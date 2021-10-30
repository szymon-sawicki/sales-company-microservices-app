package com.salescompany.orderservice.application.service;

import com.salescompany.orderservice.application.service.exception.OrdersServiceException;
import com.salescompany.orderservice.domain.configs.validator.Validator;
import com.salescompany.orderservice.domain.order.Order;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import com.salescompany.orderservice.domain.order.dto.validator.CreateUpdateOrderDtoValidator;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import com.salescompany.orderservice.infrastructure.proxy.ProductServiceProxy;
import com.salescompany.orderservice.infrastructure.proxy.UserServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;
    private final ProductServiceProxy productServiceProxy;
    private final UserServiceProxy userServiceProxy;

    public GetOrderDto create(CreateUpdateOrderDto createUpdateOrderDto) {

        checkOrder(createUpdateOrderDto);

        return orderRepository.add(createUpdateOrderDto.toOrder())
                .orElseThrow(() -> new OrdersServiceException("cannot add new order"))
                .toGetOrderDto();

    }

    public List<GetOrderDto> findAllByShop(Long id) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        return orderRepository.findAllByShopId(id)
                .stream()
                .map(Order::toGetOrderDto)
                .toList();

    }

    public List<GetOrderDto> findAllByCustomer(Long id) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        return orderRepository.findAllByCustomerId(id)
                .stream()
                .map(Order::toGetOrderDto)
                .toList();

    }

    public List<GetOrderDto> findAllByManager(Long id) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        return orderRepository.findAllByManagerId(id)
                .stream()
                .map(Order::toGetOrderDto)
                .toList();

    }

    public GetOrderDto findById(Long id) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        return orderRepository.findById(id)
                .orElseThrow(() -> new OrdersServiceException("cannot find order by id"))
                .toGetOrderDto();

    }

    public GetOrderDto delete(Long id) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        var orderToDelete = orderRepository.findById(id)
                .orElseThrow(() -> new OrdersServiceException("cannot find order to delete"));

        orderRepository.delete(id);

        return orderToDelete.toGetOrderDto();

    }

    public GetOrderDto update(Long id,CreateUpdateOrderDto createUpdateOrderDto) {

        if (id == null) {
            throw new OrdersServiceException("id is null");
        }

        if (id <= 0) {
            throw new OrdersServiceException("id is 0 or negative");
        }

        if(orderRepository.findById(id).isEmpty()) {
            throw new OrdersServiceException("cannot find order to update");
        }

        checkOrder(createUpdateOrderDto);

        var orderToUpdate = createUpdateOrderDto.toOrder().withId(id);

        return orderRepository.add(orderToUpdate)
                .orElseThrow(() -> new OrdersServiceException("cannot update order"))
                .toGetOrderDto();
    }

    public List<GetOrderDto> findAllByIds(List<Long> ids) {

        if(ids == null) {
            throw new OrdersServiceException("ids are null");
        }

        if(ids.isEmpty()) {
            throw new OrdersServiceException("ids are empty");
        }

        return orderRepository.findAllById(ids)
                .stream()
                .map(Order::toGetOrderDto)
                .toList();

    }


    private void checkOrder(CreateUpdateOrderDto createUpdateOrderDto) {

        Validator.validate(new CreateUpdateOrderDtoValidator(), createUpdateOrderDto);

        if (userServiceProxy.findById(createUpdateOrderDto.getCustomerId()) == null) {
            throw new OrdersServiceException("cannot find customer");
        }

        if (userServiceProxy.findById(createUpdateOrderDto.getManagerId()) == null) {
            throw new OrdersServiceException("cannot find manager");
        }

        var products = createUpdateOrderDto.getProductsMap()
                .keySet();

        var idsToCheck = products
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));


        if (productServiceProxy.findAllByIds(idsToCheck).size() != products.size()) {
            throw new OrdersServiceException("cannot find all products");
        }

    }



}
