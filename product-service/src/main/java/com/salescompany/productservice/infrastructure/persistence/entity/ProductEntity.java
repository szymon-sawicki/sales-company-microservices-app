package com.salescompany.productservice.infrastructure.persistence.entity;

import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "products")
@ToString

public class ProductEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private Category category;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

    @OneToOne
    private WarrantyPolicyEntity warrantyPolicy;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(category)
                .producer(producer.toProducer())
                .warrantyPolicy(warrantyPolicy.toWarrantyPolicy())
                .build();
    }
}
