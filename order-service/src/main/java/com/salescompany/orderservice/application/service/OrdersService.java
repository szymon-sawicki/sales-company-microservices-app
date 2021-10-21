package com.salescompany.orderservice.application.service;

import com.salescompany.orderservice.application.service.exception.OrdersServiceException;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import com.salescompany.orderservice.infrastructure.proxy.ProductServiceProxy;
import com.salescompany.orderservice.infrastructure.proxy.UserServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;
    private final ProductServiceProxy productServiceProxy;
    private final UserServiceProxy userServiceProxy;

    GetOrderDto create(CreateUpdateOrderDto createUpdateOrderDto) {
        // TODO validate

        if (userServiceProxy.findById(createUpdateOrderDto.getCustomerId()) != null) {
            throw new OrdersServiceException("cannot find customer");
        }

        if (userServiceProxy.findById(createUpdateOrderDto.getManagerId()) != null) {
            throw new OrdersServiceException("cannot find manager");
        }

       // var productsIds = createUpdateOrderDto.getProductsId();

    /*    var idsToCheck = productsIds
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        if(productServiceProxy.findAllByIds(idsToCheck).size() != productsIds.size()) {
            throw new OrdersServiceException("cannot find all products");
        }*/


        // sprawdzamy czy produkty i userzy istnieja
        //collection table -> id of products in entity

        return null;

    }

}
