package com.example.commerceTool.service;


import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.category.Category;
import com.example.commerceTool.config.CommerceToolsClient;
import io.vrap.rmf.base.client.ApiHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CategoryService {
    @Autowired
    private CommerceToolsClient commerceToolsClient;

    public CompletableFuture<Category> getCategoryById(String categoryId) {
        ProjectApiRoot apiRoot = commerceToolsClient.getInstance();

        return apiRoot.categories()
                .withId(categoryId)
                .get().execute().thenApply(ApiHttpResponse::getBody);
    }

    public CompletableFuture<Category> getCategoryByKey(String key) {
        ProjectApiRoot apiRoot = commerceToolsClient.getInstance();
        return apiRoot.categories()
                .withKey(key)
                .get()
                .execute().thenApply(ApiHttpResponse::getBody);
    }
}
