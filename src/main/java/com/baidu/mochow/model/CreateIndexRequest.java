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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.baidu.mochow.model.entity.IndexField;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateIndexRequest extends AbstractMochowRequest {
    private String database;
    private String table;
    private List<IndexField> indexes;

    private CreateIndexRequest(CreateIndexRequest.Builder builder) {
        this.database = builder.database;
        this.table = builder.table;
        this.indexes = builder.indexes;
    }

    public static CreateIndexRequest.Builder builder() {
        return new CreateIndexRequest.Builder();
    }

    public static class Builder {
        private String database;
        private String table;
        private List<IndexField> indexes;

        private Builder() {
            this.indexes = new ArrayList<>();
        }

        public CreateIndexRequest.Builder database(String database) {
            this.database = database;
            return this;
        }

        public CreateIndexRequest.Builder table(String table) {
            this.table = table;
            return this;
        }

        public CreateIndexRequest.Builder addIndex(IndexField field) {
            this.indexes.add(field);
            return this;
        }

        public CreateIndexRequest build() {
            return new CreateIndexRequest(this);
        }
    }
}