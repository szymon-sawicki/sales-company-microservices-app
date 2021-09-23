package com.salescompany.usersservice.domain.user.dto.validator;

import com.salescompany.usersservice.domain.configs.validator.Validator;
import com.salescompany.usersservice.domain.configs.validator.ValidatorException;
import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @Test
    @DisplayName("when username have wrong format")
    public void test4() {

        var user = CreateUpdateUserDto.builder()
                .username("asef$mss")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("username: have wrong format");

    }

    @Test
    @DisplayName("when birthdate is in the future")
    public void test5() {

        var user = CreateUpdateUserDto.builder()
                .birthDate(LocalDate.now().plusYears(10))
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("birth date: is out of range");

    }

    @Test
    @DisplayName("when password is null")
    public void test6() {

        var user = CreateUpdateUserDto.builder()
                .password(null)
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("password: is null");

    }


    @Test
    @DisplayName("when mail is null")
    public void test7() {

        var user = CreateUpdateUserDto.builder()
                .mail(null)
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("mail: is null");
    }

    @Test
    @DisplayName("when mail have wrong format (without @)")
    public void test8() {

        var user = CreateUpdateUserDto.builder()
                .mail("example_mail.gmail.com")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("mail: have wrong format");
    }


    @Test
    @DisplayName("when mail have wrong format (incorrect characters)")
    public void test9() {

        var user = CreateUpdateUserDto.builder()
                .mail("example@@mail@gmail.com")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("mail: have wrong format");
    }

    @Test
    @DisplayName("when mail is correct")
    public void test10() {

        var user = CreateUpdateUserDto.builder()
                .mail("szymon.sawicki@gmail.com")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .hasMessageNotContaining("mail");
    }

    @Test
    @DisplayName("when username is null")
    public void test11() {
        var user = CreateUpdateUserDto.builder()
                .username(null)
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("username: is null");
    }

    @Test
    @DisplayName("when username contains illegal characters")
    public void test12() {
        var user = CreateUpdateUserDto.builder()
                .username("olabiooga%@)")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("username: have wrong format");
    }

    @Test
    @DisplayName("when username is too short")
    public void test13() {
        var user = CreateUpdateUserDto.builder()
                .username("kiti")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("username: have wrong format");
    }

    @Test
    @DisplayName("when birth date is null")
    public void test14() {
        var user = CreateUpdateUserDto.builder()
                .birthDate(null)
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("birth date: is null");
    }

    @Test
    @DisplayName("when password have wrong format")
    public void test15() {
        var user = CreateUpdateUserDto.builder()
                .password("123aaaAA")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("password: have wrong format");
    }

    @Test
    @DisplayName("when password have wrong format")
    public void test18() {
        var user = CreateUpdateUserDto.builder()
                .password("123aaaAA$$")
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageNotContaining("password");
    }

    @Test
    @DisplayName("when birth date is one year ago")
    public void test16() {
        var user = CreateUpdateUserDto.builder()
                .birthDate(LocalDate.now().plusYears(3))
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("birth date: is out of range");
    }

    @Test
    @DisplayName("when gender is null")
    public void test17() {
        var user = CreateUpdateUserDto.builder()
                .gender(null)
                .build();

        var createUserDtoValidator = new CreateUpdateUserDtoValidator();

        assertThatThrownBy(() -> Validator.validate(createUserDtoValidator,user))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS]:")
                .hasMessageContaining("gender: is null");
    }


}
