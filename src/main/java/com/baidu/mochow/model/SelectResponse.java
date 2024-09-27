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

package com.baidu.mochow.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;

import com.baidu.mochow.model.entity.Row;
import com.baidu.mochow.model.entity.GeneralParams;
import com.baidu.mochow.model.entity.GeneralParamsDeserializer;

@Getter
public class SelectResponse extends AbstractMochowResponse {
    @JsonProperty("isTruncated")
    private boolean truncated;

    @JsonDeserialize(using=GeneralParamsDeserializer.class)
    private GeneralParams nextMarker;

    private List<Row> rows;
}
