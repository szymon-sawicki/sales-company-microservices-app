package com.salescompany.orderservice.domain.order.repository;

import com.salescompany.orderservice.domain.configs.repository.CrudRepository;
import com.salescompany.orderservice.domain.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByShopId(Long id);
    List<Order> findAllByConsumerId(Long id);
    List<Order> findAllByManagerId(Long id);


}
