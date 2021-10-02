package com.salescompany.productservice.domain.product;

import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.entity.ProductEntity;
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
                .producer(producer.toGetProducerDto())
                .warrantyPolicy(warrantyPolicy.toGetWarrantyPolicyDto())
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
                .producer(producer.toEntity())
               // TODO .warrantyPolicyEntity(warrantyPolicy.toEntity())
                .build();
    }


}
