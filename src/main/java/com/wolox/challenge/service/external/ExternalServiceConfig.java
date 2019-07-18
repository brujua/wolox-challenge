package com.wolox.challenge.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExternalServiceConfig {

    @Value("${external.service.url}")
    private String serviceUrl;

    @Bean
    RestTemplate restTemplate(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.rootUri(serviceUrl).build();
    }
}
