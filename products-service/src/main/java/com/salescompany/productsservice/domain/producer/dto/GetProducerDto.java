package com.salescompany.productsservice.domain.producer.dto;

import com.salescompany.productsservice.domain.address.Address;
import com.salescompany.productsservice.domain.producer.type.Industry;
import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
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
    private Address address;
    private List<GetWarrantyPolicyDto> warrantyPolicies;
}
