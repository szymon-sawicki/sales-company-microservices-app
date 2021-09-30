package com.salescompany.orderservice.domain.order.repository;

import com.salescompany.orderservice.domain.configs.repository.CrudRepository;
import com.salescompany.orderservice.domain.order.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {


}
