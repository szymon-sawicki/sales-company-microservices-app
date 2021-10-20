package com.salescompany.productservice.infrastructure.controller;


import com.salescompany.productservice.application.service.ProductsService;
import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.product.Product;
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

        Mockito.when(productsService.findById(id))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/8")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name",Matchers.equalTo("test product1")));
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

        Mockito.when(productsService.findByName("product1"))
                .thenReturn(product);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/name/product1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name",Matchers.equalTo("product1")));
    }

    @Test
    @DisplayName("when products are searched by category")
    public void test3() throws Exception{

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

        Mockito.when(productsService.findAllByCategory(category))
                .thenReturn(List.of(product,product2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/products/category/electronic")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.iterableWithSize(2)));

    }


}
