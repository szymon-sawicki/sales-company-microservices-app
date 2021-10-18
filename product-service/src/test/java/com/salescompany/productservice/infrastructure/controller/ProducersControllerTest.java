package com.salescompany.productservice.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salescompany.productservice.application.service.ProducersService;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.type.Industry;
import org.hamcrest.Matcher;
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

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProducersController.class)
public class ProducersControllerTest {

    @MockBean
    private ProducersService producersService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when producer is searched by id")
    public void test1() throws Exception {

        var id = 6L;

        var producer = GetProducerDto.builder().name("Testex2000").id(id).build();

        when(producersService.findById(id))
                .thenReturn(producer);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/producers/6")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("Testex2000")));

    }

    @Test
    @DisplayName("when producer is searched by name")
    public void test2() throws Exception {

        var id = 6L;
        var name = "Testex2000";

        var producer = GetProducerDto.builder().name(name).id(id).build();

        when(producersService.findByName(name))
                .thenReturn(producer);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/producers/name/Testex2000")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.equalTo(6)));

    }

    @Test
    @DisplayName("when new producer is created")
    public void test3() throws Exception{

        var id = 30L;
        var name = "Test2021";

        var producerDto = GetProducerDto.builder()
                .id(id)
                .name(name)
                .build();

        var createUpdateDto = CreateUpdateProducerDto.builder()
                .name(name)
                .build();

        when(producersService.createProducer(any(CreateUpdateProducerDto.class)))
                .thenReturn(producerDto);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/producers/")
                                .content(toJson(createUpdateDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("Test2021")));
    }

    @Test
    @DisplayName("when producer is updated")
    public void test4() throws Exception{

        var id = 30L;
        var name = "Test2021";

        var producerDto = GetProducerDto.builder()
                .id(id)
                .name(name)
                .build();

        var createUpdateDto = CreateUpdateProducerDto.builder()
                .name(name)
                .build();

        when(producersService.update(anyLong(),any(CreateUpdateProducerDto.class)))
                .thenReturn(producerDto);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/producers/30")
                                .content(toJson(createUpdateDto))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("Test2021")));
    }

    @Test
    @DisplayName("when producer is deleted")
    public void test5() throws Exception{

        var id = 30L;
        var name = "Test2021";

        var producerDto = GetProducerDto.builder()
                .id(id)
                .name(name)
                .build();


        when(producersService.delete(id))
                .thenReturn(producerDto);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/producers/30")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", Matchers.equalTo("Test2021")));
    }

    @Test
    @DisplayName("when list of all producers is returned")
    public void test6() throws Exception{

        var id = 30L;
        var name = "Test2021";
        var id2 = 20L;
        var name2 = "Testix";

        var producer1 = GetProducerDto.builder()
                .id(id)
                .name(name)
                .build();

        var producer2 = GetProducerDto.builder()
                .id(id2)
                .name(name2)
                .build();

        when(producersService.findAll())
                .thenReturn(List.of(producer1,producer2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/producers/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.iterableWithSize(2)));
    }

    @Test
    @DisplayName("when list of all producers of given industry is returned")
    public void test7() throws Exception{

        var id = 30L;
        var name = "Test2021";
        var industry = Industry.ELECTRONIC;
        var id2 = 20L;
        var name2 = "Testix";

        var producer1 = GetProducerDto.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .build();

        var producer2 = GetProducerDto.builder()
                .id(id2)
                .name(name2)
                .industry(industry)
                .build();

        when(producersService.findAllByIndustry(any()))
                .thenReturn(List.of(producer1,producer2));

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/producers/industry/electronic")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",Matchers.iterableWithSize(2)));

    }

    private static <T> String toJson(T t) {
        try {
            return new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
