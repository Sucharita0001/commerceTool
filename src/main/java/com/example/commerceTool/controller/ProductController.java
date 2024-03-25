package com.example.commerceTool.controller;

import com.commercetools.api.models.product.Product;
import com.example.commerceTool.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/id/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return Mono.fromFuture(productService.getProductById(id));
    }

    @GetMapping("/key/{key}")
    public Mono<Product> getProductByKey(@PathVariable String key) {
        return Mono.fromFuture(productService.getCategoryByKey(key));
    }
}