package com.example.commerceTool.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.BaseAddress;
import com.commercetools.api.models.common.BaseAddressImpl;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.customer_group.CustomerGroupReference;
import com.commercetools.api.models.customer_group.CustomerGroupResourceIdentifier;
import com.commercetools.api.models.customer_group.CustomerGroupResourceIdentifierImpl;
import com.commercetools.api.models.error.ResourceNotFoundError;
import com.commercetools.api.models.error.ResourceNotFoundErrorImpl;
import com.example.commerceTool.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.List.of;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private ProjectApiRoot apiRoot;

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
        return apiRoot.customers().post(customerDraft).executeBlocking().getBody();
    }

    //  TODO: CREATE a email verification token
    public CustomerToken generateCustomerVerificationToken(final String cutomerId){
        CustomerCreateEmailToken customerCreateEmailToken = new CustomerCreateEmailTokenImpl();
        customerCreateEmailToken.setId(cutomerId);
        customerCreateEmailToken.setTtlMinutes(60L);
        return apiRoot.customers().emailToken().post(customerCreateEmailToken).executeBlocking().getBody();
    }

    //TODO: Verify customer
    public Customer verifyCustomer(final String token){
        CustomerEmailVerify customerEmailVerify=new CustomerEmailVerifyImpl();
        customerEmailVerify.setTokenValue(token);
        return apiRoot.customers().emailConfirm().post(customerEmailVerify).executeBlocking().getBody();
    }

    // TODO: GET a customer

    public Customer getCustomerById(final String cutomerId){
        return apiRoot.customers().withId(cutomerId).get().executeBlocking().getBody();
    }

    // TODO: GET a customer group
    public Object getCustomerGroup(final String customerId){
        CustomerGroupReference customerGroupReference = apiRoot.customers().withId(customerId).get().executeBlocking().getBody().getCustomerGroup();
        if(customerGroupReference!=null){
            return customerGroupReference.getObj();
        }else{
            ResourceNotFoundError resourceNotFoundError=new ResourceNotFoundErrorImpl();
            resourceNotFoundError.setMessage("No customer group is assigned to this customer.");
            resourceNotFoundError.setValue("ERROR_404","CUSTOMER_GROUP_NOT_ASSIGNED");
            return resourceNotFoundError;
        }
    }

    // TODO: ASSIGN the customer to the customer group
    public Customer assigntCustomerGroup(final String customerId, final String customerGroupId, final long customerVersion){
        CustomerGroupResourceIdentifier customerGroupResourceIdentifier=new CustomerGroupResourceIdentifierImpl();
        customerGroupResourceIdentifier.setId(customerGroupId);
        CustomerSetCustomerGroupAction customerSetCustomerGroupAction=new CustomerSetCustomerGroupActionImpl();
        customerSetCustomerGroupAction.setCustomerGroup(customerGroupResourceIdentifier);
        CustomerUpdate customerUpdate=new CustomerUpdateImpl();
        customerUpdate.setActions(of(customerSetCustomerGroupAction));
        customerUpdate.setVersion(customerVersion);
        return apiRoot.customers().withId(customerId).post(customerUpdate).executeBlocking().getBody();
    }
}
