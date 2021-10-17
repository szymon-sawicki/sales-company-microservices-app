package com.salescompany.productservice.infrastructure.persistence.dao;

import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProducerEntityDao extends JpaRepository<ProducerEntity,Long> {
    Optional<ProducerEntity> findByName(String name);
    List<ProducerEntity> findAllByIndustry(Industry industry);
}
