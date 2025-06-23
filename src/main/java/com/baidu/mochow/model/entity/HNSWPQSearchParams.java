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

package com.baidu.mochow.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HNSWPQSearchParams extends SearchParams {
    private int ef;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float distanceFar;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float distanceNear;
    private int limit;

    public HNSWPQSearchParams(Builder builder) {
        setLimit(builder.limit);
        this.ef = builder.ef;
        this.distanceFar = builder.distanceFar;
        this.distanceNear = builder.distanceNear;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int ef;
        private float distanceFar;
        private float distanceNear;
        private int limit;

        public Builder() {
            this.limit = 50;
            this.distanceNear = 0.0f;
            this.distanceFar = 0.0f;
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

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public HNSWPQSearchParams build() {
            return new HNSWPQSearchParams(this);
        }
    }

    @Override
    public String toString() {
        return "HNSWPQSearchParams{" +
                "ef=" + ef +
                ", distanceFar=" + distanceFar +
                ", distanceNear=" + distanceNear +
                ", limit=" + limit +
                '}';
    }
} 