package com.salescompany.orderservice.domain.order;

import java.util.function.Function;

public interface OrderUtils {
    Function<Order,Long> toId = order -> order.id;
}
