package com.example.commerceTool.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressDTO {
    private String key;
    private String region;
    private String country;
    private String city;
    private String externalId;
    private String email;
}
