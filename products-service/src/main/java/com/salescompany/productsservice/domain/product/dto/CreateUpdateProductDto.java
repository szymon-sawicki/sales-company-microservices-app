package com.salescompany.productsservice.domain.product.dto;

import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.product.Product;
import com.salescompany.productsservice.domain.product.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateProductDto {
    String name;
    BigDecimal price;
    Category category;
    Long producerId;

    public Product toProduct() {
        return Product.builder()
                .name(name)
                .price(price)
                .category(category)
                .build();
    }


}
