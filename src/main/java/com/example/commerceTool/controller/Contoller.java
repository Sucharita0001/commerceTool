package com.example.commerceTool.controller;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.project.Project;
import com.example.commerceTool.config.Client;
import com.example.commerceTool.model.CustomerDTO;
import com.example.commerceTool.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class Contoller {
    @Autowired
    private Client client;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getName")
    public String getProjectName() {

        ProjectApiRoot apiRoot = client.createApiClient();

        Project myProject = apiRoot
                .get()
                .executeBlocking()
                .getBody();

        return myProject.getName();
    }

    @GetMapping("/products")
    public ProductPagedQueryResponse getProduct() {
        ProjectApiRoot apiRoot = client.createApiClient();
        return apiRoot.products().get()
                .withQuery(c -> c.version().is(2l))
                .executeBlocking().getBody();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerSignInResult> createCustomer(@RequestBody final CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), CREATED);
    }
}
