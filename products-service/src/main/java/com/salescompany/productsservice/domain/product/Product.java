package com.salescompany.productsservice.domain.product;

import com.salescompany.productsservice.domain.product.dto.GetProductDto;
import com.salescompany.productsservice.domain.warranty_strategy.WarrantyPolicy;
import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.product.type.Category;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
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


}
