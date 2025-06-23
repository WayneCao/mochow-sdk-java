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

import java.util.Map;
import java.util.HashMap;

public class AdvancedOptions {
    private final boolean acceptPartialSuccessOnMPP;
    private final float successRateLowerBoundOnMPP;

    private AdvancedOptions(Builder builder) {
        this.acceptPartialSuccessOnMPP = builder.acceptPartialSuccessOnMPP;
        this.successRateLowerBoundOnMPP = builder.successRateLowerBoundOnMPP;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> res = new HashMap<>();
        res.put("acceptPartialSuccessOnMPP", this.acceptPartialSuccessOnMPP);
        res.put("successRateLowerBoundOnMPP", this.successRateLowerBoundOnMPP);
        return res;
    }

    public boolean isAcceptPartialSuccessOnMPP() {
        return acceptPartialSuccessOnMPP;
    }

    public float getSuccessRateLowerBoundOnMPP() {
        return successRateLowerBoundOnMPP;
    }

    public static class Builder {
        private boolean acceptPartialSuccessOnMPP = false;
        private float successRateLowerBoundOnMPP = 1.0f;

        public Builder acceptPartialSuccessOnMPP(boolean value) {
            this.acceptPartialSuccessOnMPP = value;
            return this;
        }

        public Builder successRateLowerBoundOnMPP(float value) {
            this.successRateLowerBoundOnMPP = value;
            return this;
        }

        public AdvancedOptions build() {
            return new AdvancedOptions(this);
        }
    }
} 