package com.example.commerceTool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "commercetools")
public class ConfigPropertis {
    private String clientId;
    private String clientSecret;
    private String projectKey;
    private String apiUrl;
    private String oauthUrl;
    private Map<String,String> credentials;
}
