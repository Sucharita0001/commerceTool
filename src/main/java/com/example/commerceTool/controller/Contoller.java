package com.example.commerceTool.controller;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.BaseAddress;
import com.commercetools.api.models.common.BaseAddressImpl;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftImpl;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.project.Project;
import com.example.commerceTool.config.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Contoller {
    @Autowired
    private Client client;

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
    public ResponseEntity<CustomerSignInResult> addNewCustomer() {
        CustomerDraft customerDraft = new CustomerDraftImpl();
        BaseAddress address = new BaseAddressImpl();
        address.setEmail("rupali.mukherjee@tst.com");
        address.setKey("cust-address-0001");
        address.setRegion("Kolkata");
        address.setCountry("IN");
        address.setExternalId("CUST_0001_ADDRESS_01");
        address.setCity("Kolkata");

        customerDraft.setCustomerNumber("CUST0001");
        customerDraft.setDateOfBirth(LocalDate.now().minusYears(27));
        customerDraft.setCompanyName("ABC");
        customerDraft.setTitle("Mrs.");
        customerDraft.setEmail("rupali.mukherjee@tst.com");
        customerDraft.setExternalId("CUST0001");
        customerDraft.setFirstName("Rupali");
        customerDraft.setLastName("Mukherjee");
        customerDraft.setPassword("Password");
        customerDraft.setKey("cust-0001");
        customerDraft.setAddresses(address);
        return new ResponseEntity<>(client.createApiClient().customers().post(customerDraft).executeBlocking().getBody(), HttpStatus.CREATED);
    }
}
