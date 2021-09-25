package com.salescompany.productsservice.domain.address;

import lombok.Builder;

@Builder
public class Address {
    Long id;
    String street;
    String houseNumber;
    String city;
    String zipCode;
}
