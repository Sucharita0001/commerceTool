package com.example.commerceTool.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer_group.CustomerGroup;
import com.commercetools.api.models.customer_group.CustomerGroupDraft;
import com.commercetools.api.models.customer_group.CustomerGroupDraftImpl;
import com.example.commerceTool.model.CustomerGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerGroupService {
    @Autowired
    private ProjectApiRoot apiRoot;

    public CustomerGroup createCustomerGroup(final CustomerGroupDTO customerGroupDTO){
        CustomerGroupDraft customerGroupDraft=new CustomerGroupDraftImpl();
        customerGroupDraft.setGroupName(customerGroupDTO.getGroupName());
        customerGroupDraft.setKey(customerGroupDTO.getKey());
        return apiRoot.customerGroups().post(customerGroupDraft).executeBlocking().getBody();
    }
}
