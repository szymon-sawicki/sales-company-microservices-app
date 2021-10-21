package com.salescompany.orderservice.infrastructure.persistence.entity;

import com.salescompany.orderservice.domain.order.Order;
import com.salescompany.orderservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "shop_id")
    private Long shopId;

    @ElementCollection
    @CollectionTable(name = "order_products",joinColumns = {@JoinColumn(name = "order_id",referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name="quantity")
    private HashMap<Long,Integer> productsMap;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Order toOrder() {

        return Order.builder()
                .id(id)
                .customerId(customerId)
                .managerId(managerId)
                .shopId(shopId)
                .productsMap(productsMap)
                .build();

    }

}
