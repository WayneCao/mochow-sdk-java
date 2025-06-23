/*
 * Copyright 2025 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidu.mochow.model;

import com.baidu.mochow.client.MochowClient;
import com.baidu.mochow.exception.MochowClientException;
import com.baidu.mochow.model.entity.SearchResultRow;

import java.util.List;

public class SearchIterator {
    private final String database;
    private final String table;
    private final MochowClient client;
    private final SearchIteratorArgs args;
    private int returnedCount = 0;
    private String iteratedIds = "";

    public SearchIterator(MochowClient client, SearchIteratorArgs args) {
        this.database = args.database;
        this.table = args.table;
        this.client = client;
        this.args = args;
        if (args.totalSize < args.batchSize) {
            throw new IllegalArgumentException("'totalSize' should not be less than 'batchSize'");
        }
        if (args.batchSize != args.request.getLimit()) {
            throw new IllegalArgumentException("'request.limit' should be equal with 'batchSize'");
        }
        if (!(args.request instanceof VectorTopkSearchRequest || args.request instanceof MultiVectorSearchRequest)) {
            throw new IllegalArgumentException("Only VectorTopkSearchRequest and MultiVectorSearchRequest support iterator");
        }
    }

    private void updateRequestIteratedIds(VectorSearchRequestInterface request, String iteratedIds) {
        if (request instanceof VectorTopkSearchRequest) {
            ((VectorTopkSearchRequest) request).setIteratedIds(iteratedIds);
        } else if (request instanceof MultiVectorSearchRequest) {
            ((MultiVectorSearchRequest) request).setIteratedIds(iteratedIds);
        }
    }

    public List<SearchResultRow> next() throws MochowClientException {
        if (this.returnedCount >= args.totalSize) {
            return null;
        }
        
        // Update the request with the latest iteratedIds
        if (!this.iteratedIds.isEmpty() || (this.iteratedIds.isEmpty() && this.returnedCount == 0)) {
            updateRequestIteratedIds(this.args.request, this.iteratedIds);
        }
        
        SearchRowResponse res = this.client.vectorSearch(this.database, this.table, this.args.request);
        if (res.getIteratedIds() == null) {
            throw new MochowClientException("search iterator is not supported");
        }
        int cnt = Math.min(this.args.totalSize - this.returnedCount, res.getRows().size());
        List<SearchResultRow> rows = res.getRows().subList(0, cnt);
        this.iteratedIds = res.getIteratedIds();
        this.returnedCount += cnt;
        return rows;
    }

    public void close() {
        // nothing to do
    }
} 