package com.salescompany.productsservice.domain.product;

import com.salescompany.productsservice.domain.product.dto.GetProductDto;
import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.product.type.Category;
import com.salescompany.productsservice.infrastructure.persistence.entity.ProductEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
public class Product {
    Long id;
    String name;
    BigDecimal price;
    Category category;
    Producer producer;
    WarrantyPolicy warrantyPolicy;

    public GetProductDto toGetProductDto() {
        return GetProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(producer)
                .warrantyPolicy(warrantyPolicy)
                .build();
    }

    public Product withProducer(Producer newProducer) {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(newProducer)
                .warrantyPolicy(warrantyPolicy)
                .build();
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producerEntity(producer.toEntity())
                .warrantyPolicyEntity(warrantyPolicy.toEntity())
                .build();
    }


}
