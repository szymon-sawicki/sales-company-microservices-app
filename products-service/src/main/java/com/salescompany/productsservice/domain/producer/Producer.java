package com.salescompany.productsservice.domain.producer;

import com.salescompany.productsservice.domain.address.Address;
import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.producer.type.Industry;
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

    public GetProducerDto toGetProducerDto() {
        return GetProducerDto.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
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
