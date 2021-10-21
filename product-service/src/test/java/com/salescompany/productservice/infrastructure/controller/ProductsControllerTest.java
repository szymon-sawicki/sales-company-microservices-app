package com.salescompany.productservice.infrastructure.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salescompany.productservice.application.service.ProductsService;
import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {

    @MockBean
    private ProductsService productsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when product is searched by id")
    public void test1() throws Exception {

        var id = 8L;

        var product = GetProductDto.builder()
                .id(id)
                .name("test product1")
                .category(Category.ELECTRONIC)
                .build();

        when(productsService.findById(id))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("test product1")));
    }

    @Test
    @DisplayName("when product is searched by name")
    public void test2() throws Exception {

        var id = 8L;

        var product = GetProductDto.builder()
                .id(id)
                .name("product1")
                .category(Category.ELECTRONIC)
                .build();

        when(productsService.findByName("product1"))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/name/product1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("product1")));
    }

    @Test
    @DisplayName("when products are searched by category")
    public void test3() throws Exception {

        var id = 5L;
        var id2 = 7L;
        var price = new BigDecimal("150");
        var price2 = new BigDecimal("200");
        var name = "testex1";
        var name2 = "testex2";

        var category = Category.ELECTRONIC;

        var warrantyPolicy = GetWarrantyPolicyDto.builder().build();
        var producer = GetProducerDto.builder()
                .address(GetAddressDto.builder().build())
                .warrantyPolicies(List.of(warrantyPolicy))
                .build();

        var product = GetProductDto.builder()
                .id(id)
                .name(name)
                .category(category)
                .price(price)
                .warrantyPolicy(warrantyPolicy)
                .producer(producer)
                .build();

        var product2 = GetProductDto.builder()
                .id(id2)
                .name(name2)
                .category(category)
                .price(price2)
                .warrantyPolicy(warrantyPolicy)
                .producer(producer)
                .build();

        when(productsService.findAllByCategory(category))
                .thenReturn(List.of(product, product2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/category/electronic")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.iterableWithSize(2)));
    }

    @Test
    @DisplayName("when products are searched by producer")
    public void test4() throws Exception {

        var name = "testex1";
        var name2 = "testex2";

        var producerId = 56L;

        var producer = GetProducerDto.builder()
                .build();

        var product = GetProductDto.builder()
                .name(name)
                .producer(producer)
                .build();

        var product2 = GetProductDto.builder()
                .name(name2)
                .producer(producer)
                .build();

        when(productsService.findAllByProducer(producerId))
                .thenReturn(List.of(product, product2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/producer/56")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.iterableWithSize(2)));
    }

    @Test
    @DisplayName("when product is deleted")
    public void test5() throws Exception {

        var id = 8L;

        var product = GetProductDto.builder()
                .id(id)
                .name("test product1")
                .category(Category.ELECTRONIC)
                .build();

        when(productsService.delete(id))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/products/8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("test product1")));
    }

    @Test
    @DisplayName("when products are searched by ids (should return 2)")
    public void test6() throws Exception {

        var id = 5L;
        var id2 = 7L;
        var name = "testex1";
        var name2 = "testex2";

        var category = Category.ELECTRONIC;


        var product = GetProductDto.builder()
                .id(id)
                .name(name)
                .category(category)
                .build();

        var product2 = GetProductDto.builder()
                .id(id2)
                .name(name2)
                .category(category)
                .build();

        var ids = List.of(1L, 2L);

        when(productsService.findAllById(ids))
                .thenReturn(List.of(product, product2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/ids?ids=1,2")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.iterableWithSize(2)));
    }

    @Test
    @DisplayName("when products are searched by range of price")
    public void test7() throws Exception {

        var price = new BigDecimal("150");
        var price2 = new BigDecimal("200");
        var name = "testex1";
        var name2 = "testex2";

        var minRange = new BigDecimal("50");
        var maxRange = new BigDecimal("300");

        var category = Category.ELECTRONIC;


        var product = GetProductDto.builder()
                .name(name)
                .category(category)
                .price(price)
                .build();

        var product2 = GetProductDto.builder()
                .name(name2)
                .category(category)
                .price(price2)

                .build();


        when(productsService.findAllInPriceRange(minRange, maxRange))
                .thenReturn(List.of(product, product2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/price?min=50&max=300")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.iterableWithSize(2)));
    }

    @Test
    @DisplayName("when product is updated")
    public void test8() throws Exception {

        var name = "test1";
        var id = 5L;

        var createUpdateProduct = CreateUpdateProductDto.builder()
                .name("test1")
                .build();

        var product = GetProductDto.builder()
                .id(id)
                .name(name)
                .build();

        when(productsService.update(anyLong(), any(CreateUpdateProductDto.class)))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/products/5")
                                .content(toJson(createUpdateProduct))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("test1")));

    }

    @Test
    @DisplayName("when product is created")
    public void test9() throws Exception {

        var name = "test1";
        var id = 5L;

        var createUpdateProduct = CreateUpdateProductDto.builder()
                .name("test1")
                .build();

        var product = GetProductDto.builder()
                .id(id)
                .name(name)
                .build();

        when(productsService.create(any(CreateUpdateProductDto.class)))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/products")
                                .content(toJson(createUpdateProduct))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("test1")));

    }

    private static <T> String toJson(T t) {
        try {
            return new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
