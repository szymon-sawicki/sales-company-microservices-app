package com.salescompany.orderservice.infrastructure.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity,Long> {
}
