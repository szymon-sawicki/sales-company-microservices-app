package com.salescompany.productsservice.domain.warranty_policy;

import com.salescompany.productsservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productsservice.domain.warranty_policy.type.ServiceType;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@EqualsAndHashCode
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
