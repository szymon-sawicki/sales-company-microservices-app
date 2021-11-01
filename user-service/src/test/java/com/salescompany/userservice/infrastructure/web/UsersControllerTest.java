package com.salescompany.userservice.infrastructure.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salescompany.userservice.application.service.UsersService;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.userservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.infrastructure.controller.UsersController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @MockBean
    private UsersService usersService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when user is searched by id")
    public void test1() throws Exception {

        var id = 1L;

        var user = GetUserDto.builder()
                .id(id)
                .username("andreas12")
                .build();

        when(usersService.findById(id))
                .thenReturn(user);


        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username", Matchers.equalTo("andreas12")));

    }

    @Test
    @DisplayName("when user with customer role is created")
    public void test2() throws Exception {

        var username = "TestUser1";
        var id = 2L;

        var user = CreateUpdateUserDto.builder()
                .username(username)
                .build();

        var expectedResponse = CreateUserResponseDto.builder()
                .id(id)
                .username(username)
                .build();

        when(usersService.createUser(user, Role.USER_CUSTOMER))
                .thenReturn(expectedResponse);

        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/users/customer_registration")
                                .content(toJson(user))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username", Matchers.equalTo("TestUser1")));

    }

    @Test
    @DisplayName("when user is searched by username")
    public void test3() throws Exception {

        var id = 1L;
        var username = "Andreas12";

        var user = GetUserDto.builder()
                .id(id)
                .username(username)
                .build();

        when(usersService.findByUsername(username))
                .thenReturn(user);


        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/username/Andreas12")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username", Matchers.equalTo("Andreas12")));

    }

    @Test
    @DisplayName("when user is searched by username")
    public void test4() throws Exception {

        var id = 1L;
        var username = "Andreas12";
        var mail = "halo@halo.com";

        var user = GetUserDto.builder()
                .id(id)
                .username(username)
                .build();

        when(usersService.findByMail(mail))
                .thenReturn(user);


        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/mail/halo@halo.com")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username", Matchers.equalTo("Andreas12")));

    }

    private static <T> String toJson(T t) {
        try {
            return new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
