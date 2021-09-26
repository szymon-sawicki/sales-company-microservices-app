package com.salescompany.productsservice.infrastructure.persistence.entity;

import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.warranty_policy.type.ServiceType;
import com.salescompany.productsservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
