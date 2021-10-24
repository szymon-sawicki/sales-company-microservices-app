package com.salescompany.orderservice.domain.dto.validator;

import com.salescompany.orderservice.domain.configs.validator.Validator;
import com.salescompany.orderservice.domain.configs.validator.ValidatorException;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;
import com.salescompany.orderservice.domain.order.dto.validator.CreateUpdateOrderDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateUpdateOrderDtoValidatorTest {

    @Test
    @DisplayName("when create update dto is null")
    public void test1() {

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("create update order dto: is null");

    }


    @Test
    @DisplayName("when customer id is null")
    public void test2() {

        var customer = CreateUpdateOrderDto.builder()
                .customerId(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("customer id: is null");

    }

    @Test
    @DisplayName("when customer id is 0")
    public void test3() {

        var customer = CreateUpdateOrderDto.builder()
                .customerId(0L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("customer id: is 0 or negative");

    }

    @Test
    @DisplayName("when customer id is negative")
    public void test4() {

        var customer = CreateUpdateOrderDto.builder()
                .customerId(-6L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("customer id: is 0 or negative");

    }

    @Test
    @DisplayName("when manager id is null")
    public void test5() {

        var customer = CreateUpdateOrderDto.builder()
                .managerId(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("manager id: is null");

    }

    @Test
    @DisplayName("when manager id is 0")
    public void test6() {

        var customer = CreateUpdateOrderDto.builder()
                .managerId(0L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("manager id: is 0 or negative");

    }

    @Test
    @DisplayName("when manager id is negative")
    public void test7() {

        var customer = CreateUpdateOrderDto.builder()
                .managerId(-6L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("manager id: is 0 or negative");

    }

    @Test
    @DisplayName("when shop id is null")
    public void test8() {

        var customer = CreateUpdateOrderDto.builder()
                .shopId(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("shop id: is null");

    }

    @Test
    @DisplayName("when shop id is 0")
    public void test9() {

        var customer = CreateUpdateOrderDto.builder()
                .shopId(0L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("shop id: is 0 or negative");

    }

    @Test
    @DisplayName("when shop id is negative")
    public void test10() {

        var customer = CreateUpdateOrderDto.builder()
                .shopId(-6L)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("shop id: is 0 or negative");

    }

    @Test
    @DisplayName("when products map is null")
    public void test11() {

        var customer = CreateUpdateOrderDto.builder()
                .productsMap(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("products map: is null");

    }

    @Test
    @DisplayName("when products map is empty")
    public void test12() {

        var customer = CreateUpdateOrderDto.builder()
                .productsMap(new HashMap<Long,Integer>())
                .build();

        assertThatThrownBy(() -> Validator.validate(new CreateUpdateOrderDtoValidator(), customer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("products map: is empty");

    }


}
