package com.salescompany.orderservice.infrastructure.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    Long id;
}
