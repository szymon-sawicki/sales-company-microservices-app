package com.salescompany.productservice.domain.product.dto.validator;

import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.configs.validator.ValidatorException;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.type.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CreateUpdateProductValidatorTest {

    @Test
    @DisplayName("when product is correct")
    public void test1() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var name = "The magic soap";
        var price = new BigDecimal("250");
        var category = Category.ELECTRONIC;
        var producerId = 4L;
        var warrantyPolicyId = 2L;

        var product = CreateUpdateProductDto.builder()
                .name(name)
                .price(price)
                .category(category)
                .producerId(producerId)
                .warrantyPolicyId(warrantyPolicyId)
                .build();

        assertDoesNotThrow(() -> Validator.validate(createUpdateProductDtoValidator, product));

    }

    @Test
    @DisplayName("when create update dto is null")
    public void test2() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("create update product dto: is null");

    }

    @Test
    @DisplayName("when producer id is negative")
    public void test3() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .producerId(-10L)
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("producer id: is 0 or negative");
    }

    @Test
    @DisplayName("when warranty policy id is negative")
    public void test4() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .warrantyPolicyId(-10L)
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("warranty policy id: is 0 or negative");
    }

    @Test
    @DisplayName("when warranty policy id is null,")
    public void test5() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .warrantyPolicyId(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("warranty policy id: is null");
    }

    @Test
    @DisplayName("when name contains illegal characters")
    public void test6() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .name("wrt3t5%$")
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("name: have wrong format");
    }

    @Test
    @DisplayName("when name is too long")
    public void test7() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .name("sdcvdfbfggb bfgcbn fgvn xcsdc")
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("name: have wrong format");
    }

    @Test
    @DisplayName("when price is negative")
    public void test8() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .price(new BigDecimal("-20"))
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("price: is negative");
    }

    @Test
    @DisplayName("when category is null")
    public void test9() {

        var createUpdateProductDtoValidator = new CreateUpdateProductDtoValidator();

        var product = CreateUpdateProductDto.builder()
                .category(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(createUpdateProductDtoValidator, product))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("category: is null");
    }
}
