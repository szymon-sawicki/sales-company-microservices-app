package com.salescompany.productsservice.infrastructure.persistence.dao;

import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.infrastructure.persistence.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerEntityDao extends JpaRepository<ProducerEntity,Long> {
    Optional<ProducerEntity> findByName(String name);
}
