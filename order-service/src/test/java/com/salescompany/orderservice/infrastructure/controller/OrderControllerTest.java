package com.salescompany.orderservice.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salescompany.orderservice.application.service.OrdersService;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.GetOrderDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @MockBean
    private OrdersService ordersService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when order is created")
    public void test1() throws Exception{

        var id = 50L;
        var shopId = 23L;

        var getOrderDto = GetOrderDto.builder()
                .id(id)
                .shopId(shopId)
                .build();

        var createOrderDto = GetOrderDto.builder()
                .id(id)
                .shopId(shopId)
                .build();

        when(ordersService.create(any(CreateUpdateOrderDto.class)))
                .thenReturn(getOrderDto);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/orders/")
                                .content(toJson(createOrderDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.shopId",Matchers.equalTo(23)));

    }

    @Test
    @DisplayName("when order is updated")
    public void test2() throws Exception{

        var id = 50L;
        var shopId = 23L;

        var getOrderDto = GetOrderDto.builder()
                .id(id)
                .shopId(shopId)
                .build();

        var createOrderDto = GetOrderDto.builder()
                .id(id)
                .shopId(shopId)
                .build();

        when(ordersService.update(anyLong(),any(CreateUpdateOrderDto.class)))
                .thenReturn(getOrderDto);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/orders/23")
                                .content(toJson(createOrderDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.shopId",Matchers.equalTo(23)));

    }

    private static <T> String toJson(T t) {
        try {
            return new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
