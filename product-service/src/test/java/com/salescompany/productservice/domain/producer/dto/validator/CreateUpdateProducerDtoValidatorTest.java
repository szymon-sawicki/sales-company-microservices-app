package com.salescompany.productservice.domain.producer.dto.validator;

import com.salescompany.productservice.domain.address.dto.CreateUpdateAddressDto;
import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.configs.validator.ValidatorException;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CreateUpdateProducerDtoValidatorTest {

    @Test
    @DisplayName("when create update dto is null")
    public void test1() {

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProducerDtoValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("create update producer dto: is null");
    }

    @Test
    @DisplayName("when address dto is null")
    public void test2() {

        var producer = CreateUpdateProducerDto.builder()
                .createUpdateAddressDto(null)
                .build();

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProducerDtoValidator, producer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("get address dto: is null");

    }

    @Test
    @DisplayName("when name contains illegal characters")
    public void test3() {

        var producer = CreateUpdateProducerDto.builder()
                .name("aasax$$")
                .build();

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProducerDtoValidator, producer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("name: have wrong format");

    }

    @Test
    @DisplayName("when name is too short")
    public void test4() {

        var producer = CreateUpdateProducerDto.builder()
                .name("ab")
                .build();

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProducerDtoValidator, producer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("name: have wrong format");

    }


    @Test
    @DisplayName("when warranty policies is null")
    public void test5() {

        var producer = CreateUpdateProducerDto.builder()
                .warrantyPolicies(null)
                .build();

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProducerDtoValidator, producer))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("warranty policies: are null");
    }

    @Test
    @DisplayName("when producer is correct")
    public void test6() {

        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var warrantyPolicies = List.of(GetWarrantyPolicyDto.builder().build());

        var address = CreateUpdateAddressDto.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();


        var producer = CreateUpdateProducerDto.builder()
                .name(name)
                .industry(industry)
                .createUpdateAddressDto(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var createUpdateProducerDtoValidator = new CreateUpdateProducerDtoValidator();

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> Validator.validate(createUpdateProducerDtoValidator, producer));

    }

}
