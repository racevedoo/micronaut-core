/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.reactive.rxjava2.http.client.websockets;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Secondary;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.client.ReactiveHttpClientRegistry;
import io.micronaut.inject.InjectionPoint;

/**
 * Factory interface for creating RxJava 2 {@link io.micronaut.websocket.WebSocketClient}.

 * @author Sergio del Amo
 * @since 3.0.0
 */
@Factory
public class RxWebSocketClientFactory {
    private final ReactiveHttpClientRegistry<?, ?, ?, ?> clientRegistry;

    /**
     * Default constructor.
     * @param clientRegistry The client registry
     */
    public RxWebSocketClientFactory(ReactiveHttpClientRegistry<?, ?, ?, ?> clientRegistry) {
        this.clientRegistry = clientRegistry;
    }

    /**
     * Injects a {@link RxWebSocketClient} client at the given injection point.
     * @param injectionPoint The injection point
     * @return The client
     */
    @Bean
    @Secondary
    protected RxWebSocketClient webSocketClient(@Nullable InjectionPoint<?> injectionPoint) {
        if (injectionPoint != null) {
            return new BridgedRxWebSocketClient(clientRegistry.getWebSocketClient(injectionPoint.getAnnotationMetadata()));
        }
        return new BridgedRxWebSocketClient(clientRegistry.getDefaultWebSocketClient());
    }
}