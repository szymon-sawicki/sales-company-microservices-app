package com.salescompany.orderservice.infrastructure.persistence.repository;

import com.salescompany.orderservice.domain.order.Order;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import com.salescompany.orderservice.infrastructure.persistence.dao.OrderEntityDao;
import com.salescompany.orderservice.infrastructure.persistence.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderEntityDao orderEntityDao;

    @Override
    public Optional<Order> add(Order order) {
        var insertedOrder = orderEntityDao.save(order.toEntity());
        return Optional.of(insertedOrder.toOrder());
    }

    @Override
    public Optional<Order> delete(Long id) {
        var orderToDelete = orderEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("cannot find order to delete"));

        orderEntityDao.delete(orderToDelete);
        return Optional.of(orderToDelete.toOrder());

    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderEntityDao.findById(id)
                .map(OrderEntity::toOrder);
    }

    @Override
    public List<Order> findAll() {
        return orderEntityDao.findAll()
                .stream()
                .map(OrderEntity::toOrder)
                .toList();
    }

    @Override
    public List<Order> findAllById(List<Long> ids) {
        return orderEntityDao.findAllById(ids)
                .stream()
                .map(OrderEntity::toOrder)
                .toList();
    }

    @Override
    public List<Order> findAllByShopId(List<Long> ids) {

        return orderEntityDao.findAllByShopIdIn(ids)
                .stream()
                .map(OrderEntity::toOrder)
                .toList();
    }

    @Override
    public List<Order> findAllByUserId(List<Long> ids) {

        return orderEntityDao.findAllByCustomerIdIn(ids)
                .stream()
                .map(OrderEntity::toOrder)
                .toList();
    }
}
