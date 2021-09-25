package com.salescompany.productsservice.domain.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAddressDto {
    private Long id;
    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;
}
