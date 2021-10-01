package com.salescompany.productservice.domain.product.dto;

import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
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
    private GetProducerDto producer;
    private GetWarrantyPolicyDto warrantyPolicy;
}
