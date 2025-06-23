/*
 * Copyright 2024 Baidu, Inc.
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

package com.baidu.mochow.model.entity;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchRowResultDeserializer extends JsonDeserializer<BatchSearchResultRows> {
    @Override
    public BatchSearchResultRows deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode rootNode = mapper.readTree(jp);

        BatchSearchResultRows searchRowResult = new BatchSearchResultRows();

        // Deserialize the 'rows' field
        JsonNode rowsNode = rootNode.get("rows");
        if (rowsNode != null && rowsNode.isArray()) {
            List<SearchResultRow> rows = mapper.convertValue(rowsNode, new TypeReference<List<SearchResultRow>>() {});
            searchRowResult.setRows(rows);
        }

        JsonNode vectorFloatsNode = rootNode.get("searchVectorFloats");
        if (vectorFloatsNode != null && vectorFloatsNode.isArray()) {
            List<Float> searchVectorFloats = mapper.convertValue(rowsNode, new TypeReference<List<Float>>() {});
            searchRowResult.setSearchVectorFloats(searchVectorFloats);
        }

        return searchRowResult;
    }
}
