package com.salescompany.productsservice.domain.product.dto;

import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.product.type.Category;
import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private Producer producer;
    private WarrantyPolicy warrantyPolicy;
}
