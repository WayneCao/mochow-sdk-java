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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HNSWSearchParams extends SearchParams {
    private int ef;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float distanceNear;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float distanceFar;
    private boolean pruning;

    private HNSWSearchParams(Builder builder) {
        setLimit(builder.limit);
        this.ef = builder.ef;
        this.distanceNear = builder.distanceNear;
        this.distanceFar = builder.distanceFar;
        this.pruning = builder.pruning;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int limit;
        private int ef;
        private Float distanceNear;
        private Float distanceFar;
        boolean pruning;

        public Builder() {
            this.limit = 50;
            this.distanceNear = null;
            this.distanceFar = null;
            this.pruning = true;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder ef(int ef) {
            this.ef = ef;
            return this;
        }

        public Builder distanceNear(float distanceNear) {
            this.distanceNear = distanceNear;
            return this;
        }

        public Builder distanceFar(float distanceFar) {
            this.distanceFar = distanceFar;
            return this;
        }

        public Builder pruning(boolean pruning) {
            this.pruning = pruning;
            return this;
        }

        public HNSWSearchParams build() {
            return new HNSWSearchParams(this);
        }
    }
}