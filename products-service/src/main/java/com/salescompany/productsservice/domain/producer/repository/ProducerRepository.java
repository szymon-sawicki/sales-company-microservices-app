package com.salescompany.productsservice.domain.producer.repository;

import com.salescompany.productsservice.domain.configs.repository.CrudRepository;
import com.salescompany.productsservice.domain.producer.Producer;

import java.util.Optional;

public interface ProducerRepository extends CrudRepository<Producer,Long> {
        Optional<Producer> findByName(String name);


}
