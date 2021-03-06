package com.salescompany.productservice.infrastructure.persistence.dao;

import com.salescompany.productservice.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressEntityDao extends JpaRepository<AddressEntity,Long> {

    Optional<AddressEntity> findByStreetAndHouseNumberAndCityAndZipCode(String street, String houseNumber, String city, String zipCode);
}
