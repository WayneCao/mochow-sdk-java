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

import java.util.Map;
import java.util.HashMap;

/**
 * Optional configurable params for vector search.
 *
 * For each index algorithm, the params that could be set are:
 *
 * <table border="1">
 *   <caption>Index Types and Parameters</caption>
 *   <tr>
 *     <th>IndexType</th>
 *     <th>Params</th>
 *   </tr>
 *   <tr>
 *     <td>HNSW</td>
 *     <td>ef, pruning</td>
 *   </tr>
 *   <tr>
 *     <td>HNSWPQ</td>
 *     <td>ef, pruning</td>
 *   </tr>
 *   <tr>
 *     <td>PUCK</td>
 *     <td>coarseCount</td>
 *   </tr>
 *   <tr>
 *     <td>FLAT</td>
 *     <td>(none)</td>
 *   </tr>
 * </table>
*/
public class VectorSearchConfig {
    public VectorSearchConfig() {
    }

    public VectorSearchConfig setEf(int ef) {
        this.ef = ef;
        this.hasEf = true;
        return this;
    }

    public VectorSearchConfig setPruning(boolean pruning) {
        this.pruning = pruning;
        this.hasPruning = true;
        return this;
    }

    public VectorSearchConfig setCoarseCount(int coarseCount) {
        this.coarseCount = coarseCount;
        this.hasCoarseCount = true;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();

        if (this.hasEf) {
            params.put("ef", this.ef);
        }
        if (this.hasPruning) {
            params.put("pruning", this.pruning);
        }
        if (this.hasCoarseCount) {
            params.put("searchCoarseCount", this.coarseCount);
        }
        return params;
    }

    private int ef;
    private boolean hasEf;
    private boolean pruning;
    private boolean hasPruning;
    private int coarseCount;
    private boolean hasCoarseCount;
}
