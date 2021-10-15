package com.salescompany.productservice.domain.producer.dto;

import com.salescompany.productservice.domain.address.dto.CreateUpdateAddressDto;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.warranty_policy.dto.CreateUpdateWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
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
    private List<CreateUpdateWarrantyPolicyDto> warrantyPolicies;

    public Producer toProducer() {
        return Producer.builder()
                .name(name)
                .industry(industry)
                .address(createUpdateAddressDto.toAddress())
                .warrantyPolicies(warrantyPolicies.stream().map(CreateUpdateWarrantyPolicyDto::toWarrantyPolicy).toList())
                .build();
        // TODO address/strategies

    }
}
