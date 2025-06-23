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
import java.util.Optional;

import com.baidu.mochow.model.enums.FilterMode;

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
    private final Optional<Integer> ef;
    private final Optional<Boolean> pruning;
    private final Optional<Integer> coarseCount;
    private final Optional<FilterMode> filterMode;
    private final Optional<Float> postFilterAmplificationFactor;

    private VectorSearchConfig(Builder builder) {
        this.ef = builder.ef;
        this.pruning = builder.pruning;
        this.coarseCount = builder.coarseCount;
        this.filterMode = builder.filterMode;
        this.postFilterAmplificationFactor = builder.postFilterAmplificationFactor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();

        ef.ifPresent(value -> params.put("ef", value));
        pruning.ifPresent(value -> params.put("pruning", value));
        coarseCount.ifPresent(value -> params.put("searchCoarseCount", value));
        filterMode.ifPresent(value -> params.put("filterMode", value));
        postFilterAmplificationFactor.ifPresent(value -> params.put("postFilterAmplificationFactor", value));

        return params;
    }

    /**
     * Builder for VectorSearchConfig.
     */
    public static class Builder {
        private Optional<Integer> ef = Optional.empty();
        private Optional<Boolean> pruning = Optional.empty();
        private Optional<Integer> coarseCount = Optional.empty();
        private Optional<FilterMode> filterMode = Optional.empty();
        private Optional<Float> postFilterAmplificationFactor = Optional.empty();

        public Builder() {
        }

        public Builder ef(int ef) {
            if (ef <= 0) {
                throw new IllegalArgumentException("ef must be positive");
            }
            this.ef = Optional.of(ef);
            return this;
        }

        public Builder pruning(boolean pruning) {
            this.pruning = Optional.of(pruning);
            return this;
        }

        public Builder coarseCount(int coarseCount) {
            if (coarseCount <= 0) {
                throw new IllegalArgumentException("coarseCount must be positive");
            }
            this.coarseCount = Optional.of(coarseCount);
            return this;
        }

        public Builder filterMode(FilterMode filterMode) {
            this.filterMode = Optional.of(filterMode);
            return this;
        }

        public Builder postFilterAmplificationFactor(float postFilterAmplificationFactor) {
            if (postFilterAmplificationFactor <= 1.0f) {
                throw new IllegalArgumentException("postFilterAmplificationFactor must be greater than 1.0");
            }
            this.postFilterAmplificationFactor = Optional.of(postFilterAmplificationFactor);
            return this;
        }

        public VectorSearchConfig build() {
            return new VectorSearchConfig(this);
        }
    }
}
