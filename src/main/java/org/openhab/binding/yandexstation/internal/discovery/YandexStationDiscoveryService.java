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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.yandexstation.internal.YandexStationBindingConstants;
import org.openhab.binding.yandexstation.internal.YandexStationBridge;
import org.openhab.binding.yandexstation.internal.yandexapi.response.ApiDeviceResponse;
import org.openhab.core.config.discovery.AbstractDiscoveryService;
import org.openhab.core.config.discovery.DiscoveryResult;
import org.openhab.core.config.discovery.DiscoveryResultBuilder;
import org.openhab.core.config.discovery.DiscoveryService;
import org.openhab.core.io.transport.mdns.MDNSClient;
import org.openhab.core.thing.ThingUID;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Discovery service for Yandex station
 *
 * @author Petr Shatsillo - Initial contribution
 */
@Component(service = DiscoveryService.class, configurationPid = "discovery.yandexstation")
@NonNullByDefault
public class YandexStationDiscoveryService extends AbstractDiscoveryService {
    private final Logger logger = LoggerFactory.getLogger(YandexStationDiscoveryService.class);
    @Nullable
    private Runnable scanner;
    private @Nullable ScheduledFuture<?> backgroundFuture;
    /**
     * The constant yandexTokenBridgeBusList.
     */
    public static List<YandexStationBridge> yandexTokenBridgeBusList = new ArrayList<>();
    private final MDNSClient mdnsClient;

    private static final Duration FOREGROUND_SCAN_TIMEOUT = Duration.ofMillis(200);

    /**
     * Instantiates a new Yandex station discovery service.
     */
    @Activate
    public YandexStationDiscoveryService(@Reference MDNSClient mdnsClient) {
        super(Collections.singleton(YandexStationBindingConstants.THING_TYPE_BRIDGE), 30, false);
        this.mdnsClient = mdnsClient;
    }

    @Override
    public synchronized void abortScan() {
        super.abortScan();
    }

    @Override
    protected synchronized void stopScan() {

        ScheduledFuture<?> scan = backgroundFuture;
        if (scan != null) {
            scan.cancel(true);
            backgroundFuture = null;
        }
        super.stopScan();
    }

    @Override
    protected void startScan() {
        logger.debug("Start scan");
        for (YandexStationBridge yandexToken : yandexTokenBridgeBusList) {
            List<ApiDeviceResponse> devices = yandexToken.getDevices();
            if (devices != null) {
                for (ApiDeviceResponse device : devices) {
                    logger.debug("found: {}", device.id);
                    ThingUID thingUID = new ThingUID(YandexStationBindingConstants.THING_TYPE_STATION,
                            yandexToken.getThing().getUID(), device.id);
                    DiscoveryResult resultS = DiscoveryResultBuilder.create(thingUID)
                            .withProperty("device_id", device.id).withRepresentationProperty("device_id")
                            .withLabel(device.name + " S/N: " + device.id).withBridge(yandexToken.getThing().getUID())
                            .build();
                    thingDiscovered(resultS);
                }
            }
        }
    }

    @Override
    protected void startBackgroundDiscovery() {
        logger.debug("startBackgroundDiscovery");
    }

    @Override
    protected void stopBackgroundDiscovery() {
        logger.debug("stopBackgroundDiscovery");

        super.stopBackgroundDiscovery();
    }
}
