package com.salescompany.productservice.domain.producer.repository;

import com.salescompany.productservice.domain.configs.repository.CrudRepository;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.product.type.Category;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends CrudRepository<Producer,Long> {
        Optional<Producer> findByName(String name);
        List<Producer> findAllByIndustry(Industry industry);


}
