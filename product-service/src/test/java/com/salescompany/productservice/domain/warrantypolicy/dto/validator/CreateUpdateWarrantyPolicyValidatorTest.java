package com.salescompany.productservice.domain.warrantypolicy.dto.validator;

import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.configs.validator.ValidatorException;
import com.salescompany.productservice.domain.warranty_policy.dto.CreateUpdateWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.dto.validator.CreateUpdateWarrantyPolicyDtoValidator;
import com.salescompany.productservice.domain.warranty_policy.type.ServiceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CreateUpdateWarrantyPolicyValidatorTest {

    @Test
    @DisplayName("when create update dto is null")
    public void test1() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("create update warranty policy dto: is null");

    }

    @Test
    @DisplayName("when warranty period is null")
    public void test2() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .warrantyPeriod(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("warranty period: is null");

    }

    @Test
    @DisplayName("when possible services are null")
    public void test3() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .possibleServices(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("possible services: are null");

    }

    @Test
    @DisplayName("when processing period is null")
    public void test4() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .processingPeriod(null)
                .build();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("processing period: is null");

    }

    @Test
    @DisplayName("when processing period is negative")
    public void test5() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .processingPeriod(-6)
                .build();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("processing period: is negative");

    }

    @Test
    @DisplayName("when warranty period is negative")
    public void test6() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .warrantyPeriod(-6)
                .build();

        assertThatThrownBy(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("warranty period: is negative");

    }

    @Test
    @DisplayName("when warranty policy is correct")
    public void test7() {

        var createWarrantyPolicyValidator = new CreateUpdateWarrantyPolicyDtoValidator();

        var warrantyPolicy = CreateUpdateWarrantyPolicyDto.builder()
                .warrantyPeriod(24)
                .processingPeriod(14)
                .possibleServices(List.of(ServiceType.REPAIR))
                .build();


        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> Validator.validate(createWarrantyPolicyValidator, warrantyPolicy));
    }


}
