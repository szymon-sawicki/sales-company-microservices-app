package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "producers")
@ToString

public class ProducerEntity extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    @OneToOne
    private AddressEntity addressEntity;

    // list of warranty strategies accepted by that producer
    @OneToMany
    List<WarrantyPolicyEntity> warrantyPolicies;

    @OneToMany(mappedBy="producer")
    @Builder.Default
    List<ProductEntity> products = new ArrayList<>();

    public Producer toProducer() {
        return Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(addressEntity.toAddress())
                .warrantyPolicies(warrantyPolicies
                        .stream()
                        .map(WarrantyPolicyEntity::toWarrantyPolicy)
                        .toList())
                .build();
    }

}
