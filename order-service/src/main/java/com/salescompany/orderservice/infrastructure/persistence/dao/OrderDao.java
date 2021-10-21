package com.salescompany.orderservice.infrastructure.persistence.dao;

import com.salescompany.orderservice.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity,Long> {

    Optional<Order> findAllByShopId(Long id);
    Optional<Order> findAllByUserId(Long id);

}
