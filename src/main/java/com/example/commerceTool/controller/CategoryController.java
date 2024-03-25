package com.example.commerceTool.controller;

import com.commercetools.api.models.category.Category;
import com.example.commerceTool.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/id/{id}")
    public Mono<Category> getCategoryById(@PathVariable String id) {
        return Mono.fromFuture(categoryService.getCategoryById(id));
    }

    @GetMapping("/key/{key}")
    public Mono<Category> getCategoryByKey(@PathVariable String key) {
        return Mono.fromFuture(categoryService.getCategoryByKey(key));
    }
}