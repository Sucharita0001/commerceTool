package com.example.commerceTool.config;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CommerceToolsClient {
    private ProjectApiRoot projectAPIRoot;

    private ProjectApiRoot createProjectClient() {
        return ApiRootBuilder.of()
                .defaultClient(ClientCredentials.of()
                                .withClientId("client_id")
                                .withClientSecret("client_secret")
                                .build(),
                        ServiceRegion.GCP_AUSTRALIA_SOUTHEAST1)
                .build("project_key");
    }

    public CommerceToolsClient() {
        projectAPIRoot = createProjectClient();
    }

    public ProjectApiRoot getInstance() {
        return projectAPIRoot;
    }
}
