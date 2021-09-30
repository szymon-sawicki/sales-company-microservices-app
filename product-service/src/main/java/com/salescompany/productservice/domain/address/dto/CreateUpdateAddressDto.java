package com.salescompany.productservice.domain.address.dto;

import com.salescompany.productservice.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateAddressDto {
    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;


    public Address toAddress() {
        return  Address.builder()
                .street(street)
                .houseNumber(houseNumber)
                .city(city)
                .zipCode(zipCode)
                .build();
    }
}
