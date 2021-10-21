package com.salescompany.orderservice.domain.order.repository;

import com.salescompany.orderservice.domain.configs.repository.CrudRepository;
import com.salescompany.orderservice.domain.order.Order;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findAllByShopId(Long id);
    Optional<Order> findAllByUserId(Long id);


}
