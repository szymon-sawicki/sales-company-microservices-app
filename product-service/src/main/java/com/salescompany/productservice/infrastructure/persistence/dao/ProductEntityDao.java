package com.salescompany.productservice.infrastructure.persistence.dao;

import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import com.salescompany.productservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductEntityDao extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findAllByProducer(ProducerEntity producerEntity);
    List<ProductEntity> findAllByCategory(Category category);
    List<ProductEntity> findAllByPriceBetween(BigDecimal min, BigDecimal max);
    Optional<ProductEntity> findByName(String name);
}
