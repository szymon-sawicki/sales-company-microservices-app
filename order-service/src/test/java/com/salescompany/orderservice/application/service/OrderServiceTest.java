package com.salescompany.orderservice.application.service;

import com.salescompany.orderservice.application.service.exception.OrdersServiceException;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import com.salescompany.orderservice.infrastructure.proxy.ProductServiceProxy;
import com.salescompany.orderservice.infrastructure.proxy.UserServiceProxy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @TestConfiguration
    public static class OrdersServiceTestConfiguration {

        @MockBean
        public OrderRepository orderRepository;

        @MockBean
        public ProductServiceProxy productServiceProxy;

        @MockBean
        public UserServiceProxy userServiceProxy;

        @Bean
        public OrdersService ordersService() {

            return new OrdersService(orderRepository,productServiceProxy,userServiceProxy);

        }
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceProxy userServiceProxy;

    @Autowired
    private ProductServiceProxy productServiceProxy;

    @Autowired
    private OrdersService ordersService;


    @Test
    @DisplayName("creating order, when customer with given id doesn't exist")
    public void test1() {

        var productsMap = new HashMap<Long,Integer>();
        productsMap.put(2L,45);

        var order = CreateUpdateOrderDto.builder()
                .customerId(45L)
                .shopId(6L)
                .managerId(77L)
                .productsMap(productsMap)
                .build();

        when(userServiceProxy.findById(anyLong()))
                .thenReturn(null);

        assertThatThrownBy(() -> ordersService.create(order))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("cannot find customer");



    }





}
