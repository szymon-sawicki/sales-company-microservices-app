package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "products")
@ToString

public class ProductEntity extends BaseEntity {

    String name;
    BigDecimal price;
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    ProducerEntity producerEntity;

    @OneToOne
    WarrantyPolicyEntity warrantyPolicyEntity;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(producerEntity.toProducer())
                .warrantyPolicy(warrantyPolicyEntity.toWarrantyPolicy())
                .build();
    }
}
