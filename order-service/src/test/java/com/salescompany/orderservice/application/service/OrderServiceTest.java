package com.salescompany.orderservice.application.service;

import com.salescompany.orderservice.application.service.exception.OrdersServiceException;
import com.salescompany.orderservice.domain.order.Order;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import com.salescompany.orderservice.domain.order.repository.OrderRepository;
import com.salescompany.orderservice.infrastructure.proxy.ProductServiceProxy;
import com.salescompany.orderservice.infrastructure.proxy.UserServiceProxy;
import com.salescompany.orderservice.infrastructure.proxy.dto.GetProductDto;
import com.salescompany.orderservice.infrastructure.proxy.dto.GetUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
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

            return new OrdersService(orderRepository, productServiceProxy, userServiceProxy);

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

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);

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

    @Test
    @DisplayName("creating order, when manager with given id doesn't exist")
    public void test2() {

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);

        var order = CreateUpdateOrderDto.builder()
                .customerId(45L)
                .shopId(6L)
                .managerId(77L)
                .productsMap(productsMap)
                .build();

        when(userServiceProxy.findById(anyLong()))
                .thenReturn(GetUserDto.builder().build())
                .thenReturn(null);

        assertThatThrownBy(() -> ordersService.create(order))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("cannot find manager");

    }

    @Test
    @DisplayName("creating order, when list of products from db is shorter")
    public void test3() {

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);
        productsMap.put(4L, 55);

        var order = CreateUpdateOrderDto.builder()
                .customerId(45L)
                .shopId(6L)
                .managerId(77L)
                .productsMap(productsMap)
                .build();

        when(userServiceProxy.findById(anyLong()))
                .thenReturn(GetUserDto.builder().build());

        when(productServiceProxy.findAllByIds(any()))
                .thenReturn(List.of(GetProductDto.builder().id(2L).build()));

        assertThatThrownBy(() -> ordersService.create(order))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("cannot find all products");

    }

    @Test
    @DisplayName("when order is created successfully")
    public void test4() {

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);
        productsMap.put(4L, 55);

        var customerId = 45L;
        var shopId = 6L;
        var managerId = 77L;


        var order = CreateUpdateOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        when(userServiceProxy.findById(anyLong()))
                .thenReturn(GetUserDto.builder().build());

        when(productServiceProxy.findAllByIds(any()))
                .thenReturn(List.of(
                        GetProductDto.builder().id(2L).build(),
                        GetProductDto.builder().id(4L).build()));

        when(orderRepository.add(any(Order.class)))
                .thenReturn(Optional.of(order.toOrder()));

        assertThat(ordersService.create(order))
                .isEqualTo(expectedOrder);

    }

    @Test
    @DisplayName("when orders are searched by shop, should return list with 2 orders")
    public void test5() {

        var productsMap = new HashMap<Long, Integer>();

        var customerId = 45L;
        var customerId2 = 55L;
        var shopId = 6L;
        var managerId = 77L;
        var managerId2 = 88L;


        var order = Order.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var order2 = Order.builder()
                .customerId(customerId2)
                .shopId(shopId)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder2 = GetOrderDto.builder()
                .customerId(customerId2)
                .shopId(shopId)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findAllByShopId(anyLong()))
                .thenReturn(List.of(order, order2));

        assertThat(ordersService.findAllByShop(5L))
                .containsAll(List.of(expectedOrder, expectedOrder2));

    }

    @Test
    @DisplayName("when orders are searched by shop and id is null")
    public void test6() {

        assertThatThrownBy(() -> ordersService.findAllByShop(null))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("id is null");

    }

    @Test
    @DisplayName("when orders are searched by customer, should return list with 2 orders")
    public void test7() {

        var productsMap = new HashMap<Long, Integer>();

        var customerId = 45L;
        var shopId = 6L;
        var shopId2 = 8L;
        var managerId = 77L;
        var managerId2 = 88L;


        var order = Order.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var order2 = Order.builder()
                .customerId(customerId)
                .shopId(shopId2)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder2 = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId2)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findAllByCustomerId(anyLong()))
                .thenReturn(List.of(order, order2));

        assertThat(ordersService.findAllByCustomer(5L))
                .containsAll(List.of(expectedOrder, expectedOrder2));

    }

    @Test
    @DisplayName("when orders are searched by customer and id is null")
    public void test8() {

        assertThatThrownBy(() -> ordersService.findAllByCustomer(null))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("id is null");

    }

    @Test
    @DisplayName("when orders are searched by customer and id is negative")
    public void test9() {

        assertThatThrownBy(() -> ordersService.findAllByCustomer(-5L))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("id is 0 or negative");

    }

    @Test
    @DisplayName("when orders are searched by manager, should return list with 2 orders")
    public void test10() {

        var productsMap = new HashMap<Long, Integer>();

        var customerId = 45L;
        var customerId2 = 55L;
        var shopId = 6L;
        var shopId2 = 8L;
        var managerId = 77L;


        var order = Order.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var order2 = Order.builder()
                .customerId(customerId2)
                .shopId(shopId2)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder2 = GetOrderDto.builder()
                .customerId(customerId2)
                .shopId(shopId2)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findAllByManagerId(anyLong()))
                .thenReturn(List.of(order, order2));

        assertThat(ordersService.findAllByManager(5L))
                .containsAll(List.of(expectedOrder, expectedOrder2));

    }

    @Test
    @DisplayName("when orders are searched by manager and id is null")
    public void test11() {

        assertThatThrownBy(() -> ordersService.findAllByManager(null))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("id is null");

    }

    @Test
    @DisplayName("when orders are searched by manager and id is negative")
    public void test12() {

        assertThatThrownBy(() -> ordersService.findAllByManager(-5L))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("id is 0 or negative");

    }

    @Test
    @DisplayName("when order is searched by id and doesn't exists")
    public void test13() {

        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> ordersService.findById(45L))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("cannot find order by id");

    }

    @Test
    @DisplayName("when order is searched by id and were found")
    public void test14() {

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);
        productsMap.put(4L, 55);

        var customerId = 45L;
        var shopId = 6L;
        var managerId = 77L;


        var order = Order.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        assertThat(ordersService.findById(55L))
                .isEqualTo(expectedOrder);

    }

    @Test
    @DisplayName("when orders are searched by ids, should return list with 2 orders")
    public void test15() {

        var productsMap = new HashMap<Long, Integer>();

        var customerId = 45L;
        var customerId2 = 55L;
        var shopId = 6L;
        var managerId = 77L;
        var managerId2 = 88L;
        var id = 77L;
        var id2 = 88L;


        var order = Order.builder()
                .id(id)
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var order2 = Order.builder()
                .id(id2)
                .customerId(customerId2)
                .shopId(shopId)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .id(id)
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder2 = GetOrderDto.builder()
                .id(id2)
                .customerId(customerId2)
                .shopId(shopId)
                .managerId(managerId2)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findAllById(anyList()))
                .thenReturn(List.of(order, order2));

        assertThat(ordersService.findAllByIds(List.of(77L, 88L)))
                .containsAll(List.of(expectedOrder,expectedOrder2));

    }

    @Test
    @DisplayName("when order is deleted successfully")
    public void test16() {

        var productsMap = new HashMap<Long, Integer>();
        productsMap.put(2L, 45);
        productsMap.put(4L, 55);

        var customerId = 45L;
        var shopId = 6L;
        var managerId = 77L;


        var order = Order.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        var expectedOrder = GetOrderDto.builder()
                .customerId(customerId)
                .shopId(shopId)
                .managerId(managerId)
                .productsMap(productsMap)
                .build();

        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        when(orderRepository.delete(anyLong()))
                .thenReturn(Optional.of(order));

        assertThat(ordersService.delete(56L))
                .isEqualTo(expectedOrder);

    }

    @Test
    @DisplayName("when order is deleted and cannot be found by id")
    public void test17() {

        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> ordersService.delete(34L))
                .isInstanceOf(OrdersServiceException.class)
                .hasMessageContaining("cannot find order to delete");

    }


}
