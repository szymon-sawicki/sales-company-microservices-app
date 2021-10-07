package com.salescompany.userservice.infrastructure.web;

import com.salescompany.userservice.application.service.UsersService;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.infrastructure.controller.UsersController;
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

        Mockito.when(usersService.findById(id))
                .thenReturn(user);


        ResultActions result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/users/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username",Matchers.equalTo("andreas12")));

    }

}
