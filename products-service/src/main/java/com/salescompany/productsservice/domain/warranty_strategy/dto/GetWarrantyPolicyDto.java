package com.salescompany.productsservice.domain.warranty_strategy.dto;

import com.salescompany.productsservice.domain.warranty_strategy.type.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetWarrantyPolicyDto {
    private Long id;
    private Integer warrantyPeriod;
    private Integer returningPercent;
    private Integer processingPeriod;
    private List<ServiceType> possibleServices;
}
