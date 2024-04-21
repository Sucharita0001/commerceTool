package com.example.commerceTool.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDTO {
    private String id;
    private String key;
    private String customerNumber;
    private String externalId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String title;
    private String companyName;
    private AddressDTO address;
}
