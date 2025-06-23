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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.baidu.mochow.model.enums.IndexType;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FilteringIndex extends IndexField {
    private List<FilteringIndexField> fields;

    public FilteringIndex(String indexName, String[] fields) {
        setIndexName(indexName);
        setIndexType(IndexType.FILTERING_INDEX);

        this.fields = new ArrayList<FilteringIndexField>();
        for (String field : fields) {
            this.fields.add(new FilteringIndexField(field));
        }
    }

    private FilteringIndex(FilteringIndex.Builder builder) {
        setIndexName(builder.indexName);
        setIndexType(IndexType.FILTERING_INDEX);
        this.fields = builder.fields;
    }

    public static FilteringIndex.Builder builder() {
        return new FilteringIndex.Builder();
    }

    public static class Builder {
        private String indexName;
        private List<FilteringIndexField> fields;

        private Builder() {
            this.fields = new ArrayList<>();
        }

        public FilteringIndex.Builder name(String indexName) {
            this.indexName = indexName;
            return this;
        }

        public FilteringIndex.Builder addField(FilteringIndexField field) {
            this.fields.add(field);
            return this;
        }

        public FilteringIndex build() {
            return new FilteringIndex(this);
        }
    }

}