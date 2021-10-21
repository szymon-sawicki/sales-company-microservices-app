package com.salescompany.orderservice.domain.order.dto;

import com.salescompany.orderservice.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUpdateOrderDto {

    private Long customerId;
    private Long managerId;
    private Long shopId;
    private HashMap<Long,Integer> productsMap;


    public Order toOrder() {

        return Order.builder()
                .customerId(customerId)
                .managerId(managerId)
                .shopId(shopId)
                .productsMap(productsMap)
                .build();


    }

}
