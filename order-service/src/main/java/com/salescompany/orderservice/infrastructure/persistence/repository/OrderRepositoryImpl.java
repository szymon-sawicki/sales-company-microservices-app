package com.salescompany.orderservice.infrastructure.persistence.repository;

import com.salescompany.orderservice.domain.order.Order;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    // TODO implementation
    @Override
    public Optional<Order> add(Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllById(List<Long> longs) {
        return null;
    }
}
