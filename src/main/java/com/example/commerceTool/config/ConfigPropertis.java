package com.example.commerceTool.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConfigPropertis {
    @Value("${parameters.client_id}")
    private String clientId;
    @Value("${parameters.client_secret}")
    private String clientSecret;
    @Value("${parameters.project}")
    private String projectKey;
}
