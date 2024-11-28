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
import lombok.Getter;
import lombok.Setter;

import com.baidu.mochow.model.enums.IndexType;
import com.baidu.mochow.model.enums.InvertedIndexFieldAttribute;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InvertedIndex extends IndexField {
    public InvertedIndex(String indexName, String[] fields) {
        setIndexName(indexName);
        setFields(fields);
        setIndexType(IndexType.INVERTED_INDEX);
    }

    public InvertedIndex(String indexName, String[] fields, InvertedIndexParams params) {
        setIndexName(indexName);
        setFields(fields);
        setParams(params);
        setIndexType(IndexType.INVERTED_INDEX);
    }

    public InvertedIndex(String indexName, String[] fields, InvertedIndexFieldAttribute[] attributes) {
        setIndexName(indexName);
        setFields(fields);
        setFieldAttributes(attributes);
        setIndexType(IndexType.INVERTED_INDEX);
    }

    public InvertedIndex(
            String indexName, String[] fields, InvertedIndexParams params, InvertedIndexFieldAttribute[] attributes) {
        setIndexName(indexName);
        setFields(fields);
        setParams(params);
        setFieldAttributes(attributes);
        setIndexType(IndexType.INVERTED_INDEX);
    }
}
