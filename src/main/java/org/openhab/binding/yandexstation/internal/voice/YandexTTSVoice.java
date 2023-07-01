/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
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
package org.openhab.binding.yandexstation.internal.voice;

import java.util.Locale;

import org.openhab.core.voice.Voice;

/**
 * Implementation of the Voice interface for MaryTTS
 *
 * @author Kelly Davis - Initial contribution and API
 * @author Kai Kreuzer - Refactored to updated APIs and moved to openHAB
 */
public class YandexTTSVoice implements Voice {

    private final String languageTag;

    public YandexTTSVoice(String languageTag) {
        this.languageTag = languageTag;
    }

    @Override
    public String getUID() {
        return "picotts:" + languageTag.replaceAll("[^a-zA-Z0-9_]", "");
    }

    @Override
    public String getLabel() {
        return languageTag;
    }

    @Override
    public Locale getLocale() {
        return Locale.forLanguageTag(languageTag);
    }
}
