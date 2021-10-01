package com.salescompany.orderservice.domain.order;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@EqualsAndHashCode
public class Order {

    Long id;
    Long userId;
    List<Long> productsIds;

}
