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
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HNSWPQParams implements IndexParams {
    @JsonProperty("M")
    private int m;

    @JsonProperty("efConstruction")
    private int efConstruction;

    @JsonProperty("NSQ")
    private int nsq;

    @JsonProperty("sampleRate")
    private float sampleRate;

    public HNSWPQParams(int m, int efConstruction, int nsq, float sampleRate) {
        this.m = m;
        this.efConstruction = efConstruction;
        this.nsq = nsq;
        this.sampleRate = sampleRate;
    }

    @Override
    public String toString() {
        return "HNSWPQParams{" +
                "M=" + m +
                ", efConstruction=" + efConstruction +
                ", nsq=" + nsq +
                ", sampleRate=" + sampleRate +
                '}';
    }
}