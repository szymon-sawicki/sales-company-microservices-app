package com.salescompany.productservice.domain.producer.repository;

import com.salescompany.productservice.domain.configs.repository.CrudRepository;
import com.salescompany.productservice.domain.producer.Producer;

import java.util.Optional;

public interface ProducerRepository extends CrudRepository<Producer,Long> {
        Optional<Producer> findByName(String name);


}
