package com.salescompany.productsservice.infrastructure.persistence.dao;

import com.salescompany.productsservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityDao extends JpaRepository<ProductEntity,Long> {
}
