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
package org.openhab.binding.yandexstation.internal.discovery;

import static org.openhab.binding.yandexstation.internal.YandexStationBindingConstants.THING_TYPE_BRIDGE;
import static org.openhab.binding.yandexstation.internal.YandexStationBindingConstants.THING_TYPE_STATION;

import java.util.Set;

import javax.jmdns.ServiceInfo;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.config.discovery.DiscoveryResult;
import org.openhab.core.config.discovery.mdns.MDNSDiscoveryParticipant;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.ThingUID;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Discovery service for Yandex station
 *
 * @author Petr Shatsillo - Initial contribution
 */
@NonNullByDefault
@Component
public class YandexStationLocalDiscovery implements MDNSDiscoveryParticipant {

    /** The logger */
    private Logger logger = LoggerFactory.getLogger(YandexStationLocalDiscovery.class);
    private String MDNS_SERVICE_TYPE = "_yandexio._tcp.local.";

    @Override
    public Set<ThingTypeUID> getSupportedThingTypeUIDs() {
        return Set.of(THING_TYPE_STATION, THING_TYPE_BRIDGE);
    }

    @Override
    public String getServiceType() {
        return MDNS_SERVICE_TYPE;
    }

    @Override
    public @Nullable DiscoveryResult createResult(ServiceInfo serviceInfo) {
        logger.debug("mdns {}", serviceInfo);
        return null;
    }

    @Override
    public @Nullable ThingUID getThingUID(ServiceInfo serviceInfo) {
        return null;
    }
}
