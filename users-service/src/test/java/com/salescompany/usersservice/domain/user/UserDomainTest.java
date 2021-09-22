package com.salescompany.usersservice.domain.user;

import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class UserDomainTest {

    @Test
    @DisplayName("when conversion to get user dto is correct")
    public void test1() {

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

        var createUpdateUserDto = CreateUpdateUserDto.builder()
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        var expectedUser = User.builder()
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        assertThat(createUpdateUserDto.toUser())
                .isEqualTo(expectedUser);

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
}
