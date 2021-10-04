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
}
