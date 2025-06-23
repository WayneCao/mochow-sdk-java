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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public class ArrayUpdateOperations {
    public static class Append {
        /**
         * Construct a new append operation.
         * @param value: the value to append. It can be a single value or a list of values.
         */
        public Append(Object value) {
            this.value = value;
        }

        @JsonValue
        public Object getValue() {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("operator", "append");
            map.put("value", value);
            return map;
        }

        private Object value;
    }

    public static class Remove {
        /**
         * Construct a new remove operation.
         * @param value: the value to remove. It can be a single value or a list of values.
         */
        public Remove(Object value) {
            this.value = value;
        }

        @JsonValue
        public Object getValue() {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("operator", "remove");
            map.put("value", value);
            return map;
        }

        private Object value;
    }
}
