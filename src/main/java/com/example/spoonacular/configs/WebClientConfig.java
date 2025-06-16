package com.example.spoonacular.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${api.base-url:https://api.spoonacular.com")
    private String baseUrl;

    private static final String SPOONACULAR_API_KEY = "eddf8b3646ff4672a3353bad380e670a";

    @Bean
    public WebClient buildWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("x-api-key", SPOONACULAR_API_KEY)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("User-Agent", "Spring-WebClient")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(2 * 1024 * 1024)) // 2MB buffer
                        .build())
                .build();
    }
}
