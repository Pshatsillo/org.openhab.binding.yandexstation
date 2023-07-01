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

import static org.openhab.binding.yandexstation.internal.voice.YandexTTSService.*;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openhab.core.audio.AudioFormat;
import org.openhab.core.audio.AudioStream;
import org.openhab.core.audio.ByteArrayAudioStream;
import org.openhab.core.config.core.ConfigurableService;
import org.openhab.core.voice.TTSService;
import org.openhab.core.voice.Voice;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a TTS service implementation for using MaryTTS.
 *
 * @author Kelly Davis - Initial contribution and API
 * @author Kai Kreuzer - Refactored to updated APIs and moved to openHAB
 */
@Component(configurationPid = SERVICE_PID, property = Constants.SERVICE_PID + "=" + SERVICE_PID)
@ConfigurableService(category = SERVICE_CATEGORY, label = SERVICE_NAME
        + " Text-to-Speech", description_uri = SERVICE_CATEGORY + ":" + SERVICE_ID)
public class YandexTTSService implements TTSService {

    private final Logger logger = LoggerFactory.getLogger(YandexTTSService.class);

    /**
     * Service name
     */
    static final String SERVICE_NAME = "Yandex TTS";

    /**
     * Service id
     */
    static final String SERVICE_ID = "yandextts";

    /**
     * Service category
     */
    static final String SERVICE_CATEGORY = "voice";

    /**
     * Service pid
     */
    static final String SERVICE_PID = "org.openhab." + SERVICE_CATEGORY + "." + SERVICE_ID;
    /**
     * Set of supported voices
     */
    private final Set<Voice> voices = Stream
            .of(new YandexTTSVoice("de-DE"), new YandexTTSVoice("en-US"), new YandexTTSVoice("en-GB"),
                    new YandexTTSVoice("es-ES"), new YandexTTSVoice("fr-FR"), new YandexTTSVoice("it-IT"))
            .collect(Collectors.toSet());

    private final Set<AudioFormat> audioFormats = Collections.singleton(
            new AudioFormat(AudioFormat.CONTAINER_WAVE, AudioFormat.CODEC_PCM_SIGNED, false, 16, null, 16000L));

    @Activate
    protected void activate() {
        logger.debug("TTS_ACTIVATED");
    }

    @Override
    public Set<org.openhab.core.voice.Voice> getAvailableVoices() {
        logger.warn("Initializing getAvailableVoices");
        return voices;
    }

    @Override
    public Set<AudioFormat> getSupportedFormats() {
        logger.warn("Initializing getSupportedFormats");
        return audioFormats;
    }

    @Override
    public AudioStream synthesize(String text, org.openhab.core.voice.Voice voice, AudioFormat requestedFormat) {
        logger.debug("I want to say {}", text);
        return new ByteArrayAudioStream(new byte[0], new AudioFormat(AudioFormat.CONTAINER_NONE, "", false, 0, 0, 0L));
    }

    @Override
    public String getId() {
        return "yandextts";
    }

    @Override
    public String getLabel(Locale locale) {
        return "YandexTTS";
    }
}
