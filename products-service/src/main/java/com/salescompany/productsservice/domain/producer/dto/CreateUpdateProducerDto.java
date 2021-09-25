package com.salescompany.productsservice.domain.producer.dto;

import com.salescompany.productsservice.domain.address.dto.CreateUpdateAddressDto;
import com.salescompany.productsservice.domain.address.dto.GetAddressDto;
import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.producer.type.Industry;
import com.salescompany.productsservice.domain.warranty_strategy.dto.GetWarrantyPolicyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateProducerDto {
    private String name;
    private Industry industry;
    private CreateUpdateAddressDto createUpdateAddressDto;
    private List<GetWarrantyPolicyDto> warrantyPolicies;

    public Producer toProducer() {
        return Producer.builder()
                .name(name)
                .industry(industry)
                .build();
        // TODO address/strategies

    }
}
