package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.type.ServiceType;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "warranty_policies")
@ToString

public class WarrantyPolicyEntity extends BaseEntity {

    @Column(name = "warranty_period")
    Integer warrantyPeriod;

    @Column(name = "returning_percent")
    Integer returningPercent;

    @Column(name = "processing_period")
    Integer processingPeriod;

    @ManyToOne
    ProducerEntity producer;

    @ElementCollection(targetClass = ServiceType.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    @Column(name = "possible_services")
    List<ServiceType> possibleServices;

    public WarrantyPolicy toWarrantyPolicy() {
        return WarrantyPolicy.builder()
                .id(id)
                .warrantyPeriod(warrantyPeriod)
                .returningPercent(returningPercent)
                .processingPeriod(processingPeriod)
                .possibleServices(possibleServices)
                .build();
    }
}
