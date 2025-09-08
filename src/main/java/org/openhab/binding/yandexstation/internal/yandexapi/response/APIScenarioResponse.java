/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.yandexstation.internal.yandexapi.response;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * The {@link APIScenarioResponse} is describing api common success response.
 *
 * @author Petr Shatsillo - Initial contribution
 */
@NonNullByDefault
public class APIScenarioResponse {
    public String status;
    public Scenarios[] scenarios;

    public APIScenarioResponse() {
        status = "";
        scenarios = new Scenarios[0];
    }

    public APIScenarioResponse parseJson(JsonElement jsonElement) {
        this.status = jsonElement.getAsJsonObject().get("status").getAsString();
        JsonArray scenarios = jsonElement.getAsJsonObject().get("scenarios").getAsJsonArray();
        this.scenarios = new Scenarios[scenarios.size()];
        for (int i = 0; i < scenarios.size(); i++) {
            JsonObject scenario = scenarios.get(i).getAsJsonObject();
            Scenarios scn = new Scenarios();
            scn.id = scenario.get("id").getAsString();
            scn.name = scenario.get("name").getAsString();
            scn.setTriggers(scenario.get("triggers").getAsJsonArray());
            this.scenarios[i] = scn;
        }
        return this;
    }

    public class Scenarios {
        public String id = "";
        public String name = "";
        public Triggers[] triggers = new Triggers[0];

        public void setTriggers(JsonArray trg) {
            triggers = new Triggers[trg.size()];
            for (int i = 0; i < trg.size(); i++) {
                Triggers trigger = new Triggers();
                trigger.type = trg.get(i).getAsJsonObject().get("type").getAsString();

                if (!trg.get(i).getAsJsonObject().get("value").isJsonObject()) {
                    trigger.value = trg.get(i).getAsJsonObject().get("value").getAsString();
                    triggers[i] = trigger;
                }

            }
        }
    }

    public class Triggers {
        public String type = "";
        public String value = "";
    }
}
