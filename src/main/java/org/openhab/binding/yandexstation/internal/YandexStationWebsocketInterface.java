/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 * <p>
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 * <p>
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.yandexstation.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link YandexStationWebsocketInterface} is responsible for interfacing the Websocket.
 *
 * @author Dmitry P. (d51x) - Initial contribution
 */

@NonNullByDefault
public interface YandexStationWebsocketInterface {
    public void onConnect(boolean connected);

    public void onClose(int statusCode, String reason) throws Exception;

    public void onMessage(String data);

    public void onError(Throwable cause);
}
