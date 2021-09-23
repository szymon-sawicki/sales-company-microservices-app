package com.salescompany.usersservice.application.service;

import com.salescompany.usersservice.application.service.dto.UserToActivateDto;
import com.salescompany.usersservice.application.service.exception.UsersServiceException;
import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.User;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.usersservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.usersservice.domain.user.repository.UserRepository;
import com.salescompany.usersservice.domain.verification_token.VerificationToken;
import com.salescompany.usersservice.domain.verification_token.repository.VerificationTokenRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UsersServiceTest {

    @TestConfiguration
    public static class TeamsServiceTestConfiguration {

        @MockBean
        public UserRepository userRepository;

        @MockBean
        public PasswordEncoder passwordEncoder;

        @MockBean
        public EventPublisher<UserToActivateDto> publisher;

        @MockBean
        public VerificationTokenRepository verificationTokenRepository;

        @Bean
        public UsersService usersService() {
            return new UsersService(userRepository, passwordEncoder, publisher, verificationTokenRepository);
        }

    }

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Test
    @DisplayName("when e-mail already exists in database")
    public void test1() {

        var searchedMail = "test@gmail.com";

        var userToInsert = CreateUpdateUserDto.builder()
                .username("alfonso123")
                .firstName("Alfonso")
                .lastName("Banani")
                .password("banena12A$")
                .passwordConfirmation("banena12A$")
                .mail(searchedMail)
                .birthDate(LocalDate.now().minusYears(20))
                .gender(Gender.MALE)
                .build();

        when(userRepository.findByMail(searchedMail))
                .thenReturn(Optional.of(User.builder().build()));

        assertThatThrownBy(() -> usersService.createUser(userToInsert, Role.USER_CUSTOMER))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("user with given e-mail is already present in database");
    }

    @Test
    @DisplayName("when username already exists in database")
    public void test2() {

        var searchedUsername = "alfonso123";

        var userToInsert = CreateUpdateUserDto.builder()
                .username("alfonso123")
                .firstName("Alfonso")
                .lastName("Banani")
                .password("banena12A$")
                .passwordConfirmation("banena12A$")
                .mail("banani@banani.com")
                .birthDate(LocalDate.now().minusYears(20))
                .gender(Gender.MALE)
                .build();

        when(userRepository.findByUsername(searchedUsername))
                .thenReturn(Optional.of(User.builder().build()));

        assertThatThrownBy(() -> usersService.createUser(userToInsert, Role.USER_CUSTOMER))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("user with given username is already present in database");
    }

    @Test
    @DisplayName("when inserting of user is correct")
    public void test3() {

        var username = "alfonso123";
        var firstName = "Alfonso";
        var lastName = "Banani";
        var password = "banena12A$";
        var mail = "banani@banani.com";
        var birthDate = LocalDate.now().minusYears(20);
        var gender = Gender.MALE;

        var id = 2L;

        var userToInsert = CreateUpdateUserDto.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .passwordConfirmation(password)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        var insertedUser = User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .role(Role.USER_CUSTOMER)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        var expectedResponse = CreateUserResponseDto.builder()
                .id(id)
                .username(username)
                .build();

        when(userRepository.add(any()))
                .thenReturn(Optional.of(insertedUser));

        assertThat(usersService.createUser(userToInsert, Role.USER_CUSTOMER))
                .isEqualTo(expectedResponse);

    }

    @Test
    @DisplayName("when user activation with token is correct")
    public void test4() {

        var username = "alfonso123";
        var firstName = "Alfonso";
        var lastName = "Banani";
        var password = "banena12A$";
        var mail = "banani@banani.com";
        var birthDate = LocalDate.now().minusYears(20);
        var gender = Gender.MALE;

        var id = 2L;

        var user = User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .role(Role.USER_CUSTOMER)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        when(userRepository.add(user))
                .thenReturn(Optional.of(user));

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        var token = "ascsdvdfbfgt565";

        var tokenToReturn = VerificationToken.builder()
                .id(id)
                .user(user)
                .token(token)
                .dateTime(LocalDateTime.now().plusMinutes(5))
                .build();

        when(verificationTokenRepository.findByToken(token))
                .thenReturn(Optional.of(tokenToReturn));

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(()->usersService.activateUser(token));

    }

}
