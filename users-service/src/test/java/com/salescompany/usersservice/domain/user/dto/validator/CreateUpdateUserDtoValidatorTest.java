package com.salescompany.usersservice.domain.user.dto.validator;

import com.salescompany.usersservice.domain.configs.validator.Validator;
import com.salescompany.usersservice.domain.configs.validator.ValidatorException;
import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateUpdateUserDtoValidatorTest {

    @Test
    @DisplayName("when user is correct")
    public void test1() {

        var createUpdateUserValidator = new CreateUpdateUserDtoValidator();

        var firstName = "Andreas";
        var lastName = "Bottinger";
        var username = "andibotti";
        var password = "AAndi12$.w";
        var mail = "andiiii@gmail.com";
        var gender = Gender.MALE;
        var birthDate = LocalDate.now().minusYears(20);

        var createUpdateUserDto = CreateUpdateUserDto.builder()
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        Assertions.assertDoesNotThrow(() -> Validator.validate(createUpdateUserValidator,createUpdateUserDto));
    }

    @Test
    @DisplayName("when first name have wrong format")
    public void test2() {

        var user = CreateUpdateUserDto.builder()
                .firstName("ab")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("first name: have wrong format");

    }

    @Test
    @DisplayName("when last name have wrong format")
    public void test3() {

        var user = CreateUpdateUserDto.builder()
                .lastName("asef$mss")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("last name: have wrong format");

    }
}
