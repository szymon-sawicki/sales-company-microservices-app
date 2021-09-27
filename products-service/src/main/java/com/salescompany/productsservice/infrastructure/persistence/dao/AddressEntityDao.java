package com.salescompany.productsservice.infrastructure.persistence.dao;

import com.salescompany.productsservice.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityDao extends JpaRepository<AddressEntity,Long> {
}
