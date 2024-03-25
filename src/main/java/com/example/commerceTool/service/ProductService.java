package com.example.commerceTool.service;


import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.Product;
import com.example.commerceTool.config.CommerceToolsClient;
import io.vrap.rmf.base.client.ApiHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {
    @Autowired
    private CommerceToolsClient commerceToolsClient;

    public CompletableFuture<Product> getProductById(String productId) {
        ProjectApiRoot apiRoot = commerceToolsClient.getInstance();

        return apiRoot.products()
                .withId(productId)
                .get().execute().thenApply(ApiHttpResponse::getBody);
    }

    public CompletableFuture<Product> getCategoryByKey(String key) {
        ProjectApiRoot apiRoot = commerceToolsClient.getInstance();
        return apiRoot.products()
                .withKey(key)
                .get()
                .execute().thenApply(ApiHttpResponse::getBody);
    }
}
