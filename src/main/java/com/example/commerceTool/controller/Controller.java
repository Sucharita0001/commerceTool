package com.example.commerceTool.controller;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.customer_group.CustomerGroup;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.project.Project;
import com.example.commerceTool.model.CustomerDTO;
import com.example.commerceTool.model.CustomerGroupDTO;
import com.example.commerceTool.service.CustomerGroupService;
import com.example.commerceTool.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
public class Controller {
    @Autowired
    private ProjectApiRoot apiRoot;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerGroupService customerGroupService;

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
    public ResponseEntity<CustomerSignInResult> createCustomer(@RequestBody final CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), CREATED);
    }

    @PostMapping("/createCustomerEmailVerificationToken/{id}")
    public ResponseEntity<CustomerToken> createCustomerEmailVerificationToken(@PathVariable final String id) {
        return new ResponseEntity<>(customerService.generateCustomerVerificationToken(id), OK);
    }

    @PostMapping("/verifyCustomer/{token}")
    public ResponseEntity<Customer> verifyCustomer(@PathVariable final String token) {
        return new ResponseEntity<>(customerService.verifyCustomer(token), OK);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable final String id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), OK);
    }

    @GetMapping("/getCustomerGroupByCustomerId/{customerId}")
    public ResponseEntity<Object> getCustomerGroupByCustomerId(@PathVariable final String customerId) {
        Object result = customerService.getCustomerGroup(customerId);
        if(result instanceof CustomerGroup) {
            return new ResponseEntity<>(result, OK);
        }else{
            return new ResponseEntity<>(result, NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/createCustomerGroup")
    public ResponseEntity<CustomerGroup> createCustomerGroup(@RequestBody final CustomerGroupDTO customerGroupDTO) {
        return new ResponseEntity<>(customerGroupService.createCustomerGroup(customerGroupDTO), CREATED);
    }

    @PostMapping("/assignCustomerGroup/{customerId}/{groupId}/{customerVersion}")
    public ResponseEntity<Customer> assignCustomerGroup(@PathVariable final String customerId, @PathVariable final String groupId,@PathVariable final long customerVersion) {
        return new ResponseEntity<>(customerService.assigntCustomerGroup(customerId,groupId,customerVersion), ACCEPTED);
    }
}
