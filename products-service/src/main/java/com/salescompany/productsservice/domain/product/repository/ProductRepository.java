package com.salescompany.productsservice.domain.product.repository;

import com.salescompany.productsservice.domain.configs.repository.CrudRepository;
import com.salescompany.productsservice.domain.product.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
