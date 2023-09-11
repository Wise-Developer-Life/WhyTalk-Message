package com.wisedevlife.whytalkmessage.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceClientConfiguration {
    @Bean(name = "user_service_client")
    public WebClient userServiceClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }
}
