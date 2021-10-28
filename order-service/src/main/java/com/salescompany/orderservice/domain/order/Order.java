package com.salescompany.orderservice.domain.order;

import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder
@EqualsAndHashCode
public class Order {

    Long id;
    Long customerId;
    Long managerId;
    Long shopId;
    // key is product's id, value - quantity
    Map<Long,Integer> productsMap;
    LocalDateTime dateTime;

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .id(id)
                .customerId(customerId)
                .managerId(managerId)
                .shopId(shopId)
                .productsMap(productsMap)
                .build();
    }

    public GetOrderDto toGetOrderDto() {

        return GetOrderDto.builder()
                .id(id)
                .customerId(customerId)
                .managerId(managerId)
                .shopId(shopId)
                .productsMap(productsMap)
                .build();
    }

    public Order withId(Long newId) {

        return Order.builder()
                .id(newId)
                .customerId(customerId)
                .managerId(managerId)
                .shopId(shopId)
                .productsMap(productsMap)
                .build();
    }

    }


