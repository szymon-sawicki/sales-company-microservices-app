package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @OneToMany(mappedBy = "producer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<WarrantyPolicyEntity> warrantyPolicies;

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
