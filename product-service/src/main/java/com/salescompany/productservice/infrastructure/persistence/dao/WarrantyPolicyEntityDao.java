package com.salescompany.productservice.infrastructure.persistence.dao;

import com.salescompany.productservice.infrastructure.persistence.entity.WarrantyPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyPolicyEntityDao extends JpaRepository<WarrantyPolicyEntity, Long> {
}
