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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModifyTableRequest extends AbstractMochowRequest {
    private String database;
    private String table;
    private double datanodeMemoryReservedInGB;

    private ModifyTableRequest(ModifyTableRequest.Builder builder) {
        this.database = builder.database;
        this.table = builder.table;
        this.datanodeMemoryReservedInGB = builder.datanodeMemoryReservedInGB;
    }

    public static ModifyTableRequest.Builder builder() {
        return new ModifyTableRequest.Builder();
    }

    public static class Builder {
        private String database;
        private String table;
        private double datanodeMemoryReservedInGB;

        private Builder() {
            this.datanodeMemoryReservedInGB = 0.0;
        }

        public ModifyTableRequest.Builder database(String database) {
            this.database = database;
            return this;
        }

        public ModifyTableRequest.Builder table(String table) {
            this.table = table;
            return this;
        }

        public ModifyTableRequest.Builder datanodeMemoryReservedInGB(double datanodeMemoryReservedInGB) {
            this.datanodeMemoryReservedInGB = datanodeMemoryReservedInGB;
            return this;
        }

        public ModifyTableRequest build() {
            return new ModifyTableRequest(this);
        }
    }
} 