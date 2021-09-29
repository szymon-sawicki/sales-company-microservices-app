package com.salescompany.userservice.domain.user;

import com.salescompany.userservice.domain.user.Type.Gender;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class UserDomainTest {

    @Test
    @DisplayName("when conversion to get user dto is correct")
    public void test1() {

        var id = 1L;

        var firstName = "Andreas";
        var lastName = "Bottinger";
        var username = "andibotti";
        var password = "AAndi12$.w";
        var mail = "andiiii@gmail.com";
        var role = Role.USER_CUSTOMER;
        var gender = Gender.MALE;
        var birthDate = LocalDate.now().minusYears(20);
        var creationDate = LocalDateTime.now().minusDays(5);
        var enabled = true;

        var user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .role(role)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(creationDate)
                .gender(gender)
                .build();

        var expectedDto = GetUserDto.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .role(role)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(creationDate)
                .gender(gender)
                .build();


        assertThat(user.toGetUserDto())
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when password is changed")
    public void test2() {

        var id = 5L;
        var firstName = "Andreas";
        var lastName = "Bottinger";
        var username = "andibotti";
        var password = "AAndi12$.w";
        var mail = "andiiii@gmail.com";
        var role = Role.USER_CUSTOMER;
        var gender = Gender.MALE;
        var birthDate = LocalDate.now().minusYears(20);
        var creationDate = LocalDateTime.now().minusDays(5);
        var enabled = true;

        var newPassword = "12Aaa.$Baa";

        var user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .creationDateTime(creationDate)
                .role(role)
                .enabled(enabled)
                .build();

        var expectedUser = User.builder()
                .username(username)
                .password(newPassword)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(creationDate)
                .gender(gender)
                .role(role)
                .enabled(enabled)
                .build();

        assertThat(user.withPassword(newPassword))
                .isEqualTo(expectedUser);

    }

    @Test
    @DisplayName("when creation date time is changed")
    public void test3() {

        var id = 5L;
        var firstName = "Andreas";
        var lastName = "Bottinger";
        var username = "andibotti";
        var password = "AAndi12$.w";
        var mail = "andiiii@gmail.com";
        var role = Role.USER_CUSTOMER;
        var gender = Gender.MALE;
        var birthDate = LocalDate.now().minusYears(20);
        var creationDateTime = LocalDateTime.now().minusDays(5);
        var newCreationDateTime = LocalDateTime.now().minusDays(3);
        var enabled = true;

        var user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .creationDateTime(creationDateTime)
                .role(role)
                .enabled(enabled)
                .build();

        var expectedUser = User.builder()
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(newCreationDateTime)
                .gender(gender)
                .role(role)
                .enabled(enabled)
                .build();

        assertThat(user.withCreationDateTime(creationDateTime))
                .isEqualTo(expectedUser);

    }

    @Test
    @DisplayName("when conversion to user entity is correct")
    public void test4() {

        var id = 1L;

        var firstName = "Andreas";
        var lastName = "Bottinger";
        var username = "andibotti";
        var password = "AAndi12$.w";
        var mail = "andiiii@gmail.com";
        var role = Role.USER_CUSTOMER;
        var gender = Gender.MALE;
        var birthDate = LocalDate.now().minusYears(20);
        var creationDate = LocalDateTime.now().minusDays(5);
        var enabled = true;

        var user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .role(role)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(creationDate)
                .gender(gender)
                .build();

        var expectedEntity = UserEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .role(role)
                .lastName(lastName)
                .birthDate(birthDate)
                .creationDateTime(creationDate)
                .gender(gender)
                .build();


        assertThat(user.toEntity())
                .isEqualTo(expectedEntity);

    }

}
