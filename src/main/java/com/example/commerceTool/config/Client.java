package com.example.commerceTool.config;

import com.commercetools.api.client.ProjectApiRoot;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.commercetools.api.defaultconfig.ApiRootBuilder.of;
import static com.commercetools.api.defaultconfig.ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class Client {

    @Autowired
    private ConfigPropertis configPropertis;

    @Bean
    public ProjectApiRoot createApiClient() {
        if (isEmpty(configPropertis.getCredentials().get("username")) || isEmpty(configPropertis.getCredentials().get("password"))) {
            return of().withAnonymousSessionFlow(ClientCredentials.of()
                            .withClientId(configPropertis.getClientId())
                            .withClientSecret(configPropertis.getClientSecret())
                            .build(), buildTokenEndpoint()).withApiBaseUrl(configPropertis.getApiUrl() + "/")
                    .build(configPropertis.getProjectKey());
        }
        return of()
                .defaultClient(ClientCredentials.of()
                                .withClientId(configPropertis.getClientId())
                                .withClientSecret(configPropertis.getClientSecret())
                                .build(),
                        GCP_AUSTRALIA_SOUTHEAST1)
                .build(configPropertis.getProjectKey());
    }

    private @NotNull String buildTokenEndpoint() {
        return configPropertis.getOauthUrl() + "/oauth/" + configPropertis.getProjectKey() + "/anonymous/token";
    }
}
