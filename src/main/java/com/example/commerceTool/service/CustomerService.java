package com.example.commerceTool.service;

import com.commercetools.api.models.common.BaseAddress;
import com.commercetools.api.models.common.BaseAddressImpl;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftImpl;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.example.commerceTool.config.Client;
import com.example.commerceTool.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private Client client;

    // TODO: CREATE a customer
    public CustomerSignInResult createCustomer(final CustomerDTO customerDTO){
        CustomerDraft customerDraft = new CustomerDraftImpl();
        BaseAddress address = new BaseAddressImpl();
        address.setEmail(customerDTO.getEmail());

        address.setKey(customerDTO.getAddress().getKey());
        address.setRegion(customerDTO.getAddress().getRegion());
        address.setCountry(customerDTO.getAddress().getCountry());
        address.setExternalId(customerDTO.getAddress().getExternalId());
        address.setCity(customerDTO.getAddress().getCity());

        customerDraft.setCustomerNumber(customerDTO.getCustomerNumber());
        customerDraft.setCompanyName(customerDTO.getCompanyName());
        customerDraft.setTitle(customerDTO.getTitle());
        customerDraft.setEmail(customerDTO.getEmail());
        customerDraft.setExternalId(customerDTO.getExternalId());
        customerDraft.setFirstName(customerDTO.getFirstName());
        customerDraft.setLastName(customerDTO.getLastName());
        customerDraft.setPassword(customerDTO.getPassword());
        customerDraft.setKey(customerDTO.getKey());
        customerDraft.setAddresses(address);
        log.info("Creating customer {}", customerDraft);
        return client.createApiClient().customers().post(customerDraft).executeBlocking().getBody();
    }
    //  CREATE a email verification token
    public void generateCustomerVerificationToken(){

    }
}
