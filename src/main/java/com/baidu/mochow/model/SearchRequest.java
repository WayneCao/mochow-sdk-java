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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.baidu.mochow.model.entity.ANNSearchParams;
import com.baidu.mochow.model.entity.GeneralParams;
import com.baidu.mochow.model.entity.Vector;
import com.baidu.mochow.model.entity.VectorSearchConfig;
import com.baidu.mochow.model.enums.ReadConsistency;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchRequest extends AbstractMochowRequest {
    private String database;
    private String table;
    private ANNSearchParams anns;
    private GeneralParams partitionKey;
    boolean retrieveVector;
    List<String> projections;
    ReadConsistency readConsistency;

    public SearchRequest(Builder builder) {
        this.database = builder.database;
        this.table = builder.table;
        this.anns = builder.anns;
        this.partitionKey = builder.partitionKey;
        this.retrieveVector = builder.retrieveVector;
        this.projections = builder.projections;
        this.readConsistency = builder.readConsistency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String database;
        private String table;
        private ANNSearchParams anns;
        private GeneralParams partitionKey;
        boolean retrieveVector;
        List<String> projections;
        ReadConsistency readConsistency;

        public Builder() {
            this.projections = new ArrayList<String>();
            this.retrieveVector = false;
            this.readConsistency = ReadConsistency.EVENTUAL;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder table(String table) {
            this.table = table;
            return this;
        }

        public Builder anns(ANNSearchParams anns) {
            this.anns = anns;
            return this;
        }

        public Builder retrieveVector(Boolean retrieveVector) {
            this.retrieveVector = retrieveVector;
            return this;
        }

        public Builder readConsistency(ReadConsistency readConsistency) {
            this.readConsistency = readConsistency;
            return this;
        }

        public Builder projections(List<String> projections) {
            this.projections = projections;
            return this;
        }

        public Builder addPartitionKey(String key, Object value) {
            if (this.partitionKey == null) {
                this.partitionKey = new GeneralParams();
            }
            this.partitionKey.add(key, value);
            return this;
        }

        public Builder partitionKey(GeneralParams partitionKey) {
            this.partitionKey = partitionKey;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(this);
        }
    }

    static class SearchCommonFields {
        public GeneralParams partitionKey;
        public List<String> projections;
        public ReadConsistency readConsistency;
        public boolean hasLimit;
        public int limit;
        public String filter;
    }

    static Map<String, Object> searchCommonFieldsToMap(SearchCommonFields fields) {
        Map<String, Object> map = new HashMap<>();
        if (fields.partitionKey != null) {
            map.put("partitionKey", fields.partitionKey);
        }
        if (fields.projections != null) {
            map.put("projections", fields.projections);
        }
        if (fields.readConsistency != null) {
            map.put("readConsistency", fields.readConsistency);
        }
        if (fields.hasLimit) {
            map.put("limit", fields.limit);
        }
        if (fields.filter != null) {
            map.put("filter", fields.filter);
        }
        return map;
    }

    static class VectorSearchFields extends SearchCommonFields {
        public void fillSearchFields(Map<String, Object> fields) {
            // anns.params
            Map<String, Object> params = new HashMap<>();
            if (hasDistanceNear) {
                params.put("distanceNear", distanceNear);
            }
            if (hasDistanceFar) {
                params.put("distanceFar", distanceFar);
            }
            if (hasLimit) {
                params.put("limit", limit);
            }
            if (config != null) {
                config.toMap().forEach(params::put);
            }

            // anns
            Map<String, Object> anns = new HashMap<>();
            if (!vectorField.isEmpty()) {
                anns.put("vectorField", vectorField);
            }
            if (vector != null) {
                anns.put(vector.name(), vector.representation());
            }
            if (vectors != null && !vectors.isEmpty()) {
                List<Object> vecs = new ArrayList<>();
                for (Vector v : vectors) {
                    vecs.add(v.representation());
                }
                anns.put(vectors.get(0).name(), vecs);
            }
            if (filter != null) {
                anns.put("filter", filter);
            }
            if (!params.isEmpty()) {
                anns.put("params", params);
            }
            if (!anns.isEmpty()) {
                fields.put("anns", anns);
            }

            // common fields
            for (Map.Entry<String, Object> entry : searchCommonFieldsToMap((SearchCommonFields) this).entrySet()) {
                if (entry.getKey().equals("filter") || entry.getKey().equals("limit")) { // in "anns"
                    continue;
                }
                fields.put(entry.getKey(), entry.getValue());
            }
        }

        public String vectorField;
        public Vector vector;
        public List<Vector> vectors;
        public boolean hasDistanceNear;
        public float distanceNear;
        public boolean hasDistanceFar;
        public float distanceFar;
        public VectorSearchConfig config;
    }

    static class BM25SearchFields extends SearchCommonFields {
        public void fillSearchFields(Map<String, Object> fields) {
            for (Map.Entry<String, Object> entry : searchCommonFieldsToMap((SearchCommonFields) this).entrySet()) {
                fields.put(entry.getKey(), entry.getValue());
            }

            Map<String, Object> params = new HashMap<>();
            params.put("indexName", indexName);
            params.put("searchText", searchText);

            fields.put("BM25SearchParams", params);
        }

        public String indexName;
        public String searchText;
    }

    static class HybridSearchFields extends SearchCommonFields{
        public void fillSearchFields(Map<String, Object> fields) {
            if (vectorRequest != null) {
                vectorRequest.toMap().forEach(fields::put);
            }

            if (bm25Request != null) {
                bm25Request.toMap().forEach(fields::put);

            }

            for (Map.Entry<String, Object> entry : searchCommonFieldsToMap((SearchCommonFields) this).entrySet()) {
                fields.put(entry.getKey(), entry.getValue());
            }

            if (fields.containsKey("anns")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> anns = (Map<String, Object>) fields.get("anns");
                anns.put("weight", vectorWeight);
            }

            if (fields.containsKey("BM25SearchParams")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> bm25 = (Map<String, Object>) fields.get("BM25SearchParams");
                bm25.put("weight", bm25Weight);
            }
        }

        VectorSearchRequestInterface vectorRequest;
        BM25SearchRequestInterface bm25Request;
        float vectorWeight;
        float bm25Weight;
    }

    public interface SearchRequestInterface {
        String requestType();
        Map<String, Object> toMap();
    }

    public interface VectorSearchRequestInterface extends SearchRequestInterface {
    }

    public interface BM25SearchRequestInterface extends SearchRequestInterface {
    }

    public interface HybridSearchRequestInterface extends SearchRequestInterface {
    }
}
