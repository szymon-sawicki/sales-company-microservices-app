package com.salescompany.orderservice.infrastructure.persistence.dao;

import com.salescompany.orderservice.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEntityDao extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findAllByShopId(Long id);
    List<OrderEntity> findAllByCustomerId(Long id);
    List<OrderEntity> findAllByManagerId(Long id);

}
