package com.salescompany.userservice.infrastructure.persistence.entity.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;
}
