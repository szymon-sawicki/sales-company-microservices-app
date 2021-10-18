package com.salescompany.productservice.domain.product.repository;

import com.salescompany.productservice.domain.configs.repository.CrudRepository;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.type.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAllByProducer(Producer producer);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);
    Optional<Product> findByName(String name);
}
