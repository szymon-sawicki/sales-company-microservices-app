package com.salescompany.productsservice.domain.address;

import com.salescompany.productsservice.domain.address.dto.GetAddressDto;
import com.salescompany.productsservice.infrastructure.persistence.entity.AddressEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode

public class Address {
    Long id;
    String street;
    String houseNumber;
    String city;
    String zipCode;

    public GetAddressDto toGetAddressDto() {
        return GetAddressDto.builder()
                .id(id)
                .street(street)
                .houseNumber(houseNumber)
                .zipCode(zipCode)
                .build();
    }

    public AddressEntity toEntity() {
        return AddressEntity.builder()
                .id(id)
                .street(street)
                .houseNumber(houseNumber)
                .zipCode(zipCode)
                .build();
    }
}
