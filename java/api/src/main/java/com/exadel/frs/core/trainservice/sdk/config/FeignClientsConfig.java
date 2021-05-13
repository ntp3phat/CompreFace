/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.exadel.frs.core.trainservice.sdk.config;

import static com.exadel.frs.core.trainservice.system.global.EnvironmentProperties.ServerType.PYTHON;
import com.exadel.frs.core.trainservice.sdk.faces.feign.FacesFeignClient;
import com.exadel.frs.core.trainservice.system.global.EnvironmentProperties;
import feign.Feign;
import feign.Logger;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClientsConfig {

    private final EnvironmentProperties properties;

    @Bean
    public FacesFeignClient getFacesClient() {
        return Feign.builder()
                    .encoder(new SpringFormEncoder(new JacksonEncoder()))
                    .decoder(new JacksonDecoder())
                    .logLevel(Logger.Level.FULL)
                    .target(FacesFeignClient.class, properties.getServers().get(PYTHON).getUrl());
    }
}