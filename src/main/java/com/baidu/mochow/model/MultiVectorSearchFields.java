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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baidu.mochow.model.entity.FusionRankPolicy;
import com.baidu.mochow.model.entity.AdvancedOptions;

public class MultiVectorSearchFields extends SearchCommonFields {
    public List<SingleVectorSearchRequestInterface> requests;
    public FusionRankPolicy ranking;
    public AdvancedOptions advancedOptions;
    public String iteratedIds = "";
    public boolean hasIteratedIds;

    public void fillSearchFields(Map<String, Object> fields) {
        for (Map.Entry<String, Object> entry : toMap().entrySet()) {
            fields.put(entry.getKey(), entry.getValue());
        }

        List<Object> search = new ArrayList<>();
        for (SingleVectorSearchRequestInterface request : requests) {
            search.add(request.toMap().get("anns"));
        }
        fields.put("search", search);

        if (ranking != null) {
            fields.put("ranking", ranking.representation());
        }

        if (advancedOptions != null) {
            fields.put("advancedOptions", advancedOptions.toMap());
        }

        if (hasIteratedIds) {
            fields.put("iteratedIds", iteratedIds);
        }
    }
} 