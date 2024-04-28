package com.example.commerceTool.controller;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.project.Project;
import com.example.commerceTool.model.CustomerDTO;
import com.example.commerceTool.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class Contoller {
    @Autowired
    private ProjectApiRoot apiRoot;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getName")
    public String getProjectName() {
        Project myProject = apiRoot
                .get()
                .executeBlocking()
                .getBody();

        return myProject.getName();
    }

    @GetMapping("/products")
    public ProductPagedQueryResponse getProduct() {
        return apiRoot.products().get()
                .executeBlocking().getBody();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerSignInResult> createCustomer(@RequestBody final CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), CREATED);
    }

    @PostMapping("/createCustomerEmailVerificationToken/{id}")
    public ResponseEntity<CustomerToken> createCustomerEmailVerificationToken(@PathVariable final String id){
        return new ResponseEntity<>(customerService.generateCustomerVerificationToken(id), OK);
    }
}
