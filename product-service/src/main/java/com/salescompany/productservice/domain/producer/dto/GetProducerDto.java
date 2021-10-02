package com.salescompany.productservice.domain.producer.dto;

import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.producer.type.Industry;
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
public class GetProducerDto {
    private Long id;
    private String name;
    private Industry industry;
    private GetAddressDto address;
    private List<GetWarrantyPolicyDto> warrantyPolicies;
}
