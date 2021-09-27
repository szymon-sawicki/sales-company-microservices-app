package com.salescompany.productsservice.infrastructure.persistence.dao;

import com.salescompany.productsservice.infrastructure.persistence.entity.WarrantyPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyPolicyEntityDao extends JpaRepository<WarrantyPolicyEntity, Long> {
}
