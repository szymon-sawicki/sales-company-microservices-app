package com.salescompany.productservice.domain.warranty_policy.dto;

import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.type.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateWarrantyPolicyDto {
    private Integer warrantyPeriod;
    private Integer returningPercent;
    private Integer processingPeriod;
    private List<ServiceType> possibleServices;

    public WarrantyPolicy toWarrantyPolicy() {
        return WarrantyPolicy.builder()
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .processingPeriod(processingPeriod)
                .build();
    }
}
