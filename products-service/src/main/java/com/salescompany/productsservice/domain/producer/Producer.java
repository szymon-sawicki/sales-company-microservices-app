package com.salescompany.productsservice.domain.producer;

import com.salescompany.productsservice.domain.address.Address;
import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.producer.type.Industry;
import com.salescompany.productsservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productsservice.infrastructure.persistence.entity.ProducerEntity;
import com.salescompany.productsservice.infrastructure.persistence.entity.ProductEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@EqualsAndHashCode
public class Producer {
    Long id;
    String name;
    Industry industry;
    Address address;
    // list of warranty strategies accepted by that producer
    List<WarrantyPolicy> warrantyPolicies;

    public Producer withAddress(Address newAddress) {
        return Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(newAddress)
                .warrantyPolicies(warrantyPolicies)
                .build();
    }

    public GetProducerDto toGetProducerDto() {
        return GetProducerDto.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toGetWarrantyPolicyDto).toList())
                .build();
    }

    public ProducerEntity toEntity() {
        return ProducerEntity.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .addressEntity(address.toEntity())
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toEntity).toList())
                .build();
    }
}
