package com.salescompany.orderservice.application.service.exception;

import com.salescompany.orderservice.domain.order.Order;

public class OrdersServiceException extends RuntimeException {
    public OrdersServiceException(String message) {
        super(message);
    }
}
