package com.salescompany.orderservice.infrastructure.proxy.dto;

import com.salescompany.orderservice.infrastructure.proxy.dto.type.Category;
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
    private Long producerId;
    private Long warrantyPolicyId;

}
