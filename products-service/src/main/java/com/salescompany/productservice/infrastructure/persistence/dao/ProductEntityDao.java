package com.salescompany.productservice.infrastructure.persistence.dao;

import com.salescompany.productservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityDao extends JpaRepository<ProductEntity,Long> {
}
