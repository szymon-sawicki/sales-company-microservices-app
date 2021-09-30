package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "addresses")
@ToString

public class AddressEntity extends BaseEntity {

    private String street;
    private String city;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "zip_code")
    private String zipCode;


    public Address toAddress() {
        return Address.builder()
                .id(id)
                .street(street)
                .houseNumber(houseNumber)
                .zipCode(zipCode)
                .build();
    }
}
