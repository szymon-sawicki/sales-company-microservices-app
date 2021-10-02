package com.salescompany.productservice.domain.product;

import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductDomainTest {

    @Test
    @DisplayName("when conversion to get product dto is correct")
    public void test1() {

        var id = 6L;
        var name = "The magic soap";
        var price = new BigDecimal("250");
        var category = Category.ELECTRONIC;
        var producer = Producer.builder().build();
        var warrantyPolicy = WarrantyPolicy.builder().build();

        var product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(producer)
                .warrantyPolicy(warrantyPolicy)
                .build();

        var expectedDto = GetProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(GetProducerDto.builder().build())
                .warrantyPolicy(GetWarrantyPolicyDto.builder().build())
                .build();

        Assertions.assertThat(product.toGetProductDto())
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when new producer is given")
    public void test2() {

        var id = 6L;
        var name = "The magic soap";
        var price = new BigDecimal("250");
        var category = Category.ELECTRONIC;
        var producer = Producer.builder().build();
        var warrantyPolicy = WarrantyPolicy.builder().build();

        var newProducer = Producer.builder().name("New producer").build();

        var product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(producer)
                .warrantyPolicy(warrantyPolicy)
                .build();

        var expectedProduct = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(newProducer)
                .warrantyPolicy(WarrantyPolicy.builder().build())
                .build();

        Assertions.assertThat(product.withProducer(newProducer))
                .isEqualTo(expectedProduct);

    }
}
