package com.salescompany.productservice.domain.address.repository;

import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.configs.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address,Long> {

    Optional<Address> findByAddress(String street, String houseNumber, String city, String zipCode);
}
