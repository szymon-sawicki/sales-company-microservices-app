package com.salescompany.usersservice.domain.user;

import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import org.assertj.core.api.Assertions;
import org.bouncycastle.asn1.x509.RoleSyntax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        Assertions.assertThat(createUpdateUserDto.toUser())
                .isEqualTo(expectedUser);

    }
}
