package com.salescompany.productservice.domain.product.repository;

import com.salescompany.productservice.domain.configs.repository.CrudRepository;
import com.salescompany.productservice.domain.product.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
