package com.salescompany.productsservice.domain.warranty_strategy;

import com.salescompany.productsservice.domain.warranty_strategy.dto.GetWarrantyPolicyDto;
import com.salescompany.productsservice.domain.warranty_strategy.type.ServiceType;
import lombok.Builder;

import java.util.List;

@Builder
public class WarrantyPolicy {
    Long id;
    Integer warrantyPeriod;
    Integer returningPercent;
    Integer processingPeriod;
    List<ServiceType> possibleServices;

    public GetWarrantyPolicyDto toGetWarrantyPolicyDto() {
        return GetWarrantyPolicyDto.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .processingPeriod(processingPeriod)
                .possibleServices(possibleServices)
                .build();
    }

}
