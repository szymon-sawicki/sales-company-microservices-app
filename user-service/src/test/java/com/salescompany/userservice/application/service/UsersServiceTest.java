package com.salescompany.userservice.application.service;

import com.salescompany.userservice.application.service.dto.UserToActivateDto;
import com.salescompany.userservice.application.service.exception.UsersServiceException;
import com.salescompany.userservice.domain.user.Type.Gender;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.User;
import com.salescompany.userservice.domain.user.dto.CreateUpdateUserDto;
import com.salescompany.userservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.domain.user.repository.UserRepository;
import com.salescompany.userservice.domain.verification_token.VerificationToken;
import com.salescompany.userservice.domain.verification_token.repository.VerificationTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> usersService.activateUser(token));

    }

    @Test
    @DisplayName("when token is not correct")
    public void test5() {

        when(verificationTokenRepository.findByToken(any()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.activateUser("sdvdfbfgbdfer"))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("User activation failed");
    }

    @Test
    @DisplayName("when token is expired")
    public void test6() {
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
                .dateTime(LocalDateTime.now().minusMinutes(10))
                .build();

        when(verificationTokenRepository.findByToken(token))
                .thenReturn(Optional.of(tokenToReturn));

        assertThatThrownBy(() -> usersService.activateUser(token))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("User activation failed");
    }

    @Test
    @DisplayName("when user is searched by e-mail and exists in database")
    public void test7() {

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

        var expectedUserDto = GetUserDto.builder()
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

        when(userRepository.findByMail(mail))
                .thenReturn(Optional.of(user));

        assertThat(usersService.findByMail(mail))
                .isEqualTo(expectedUserDto);

    }

    @Test
    @DisplayName("when user with given mail doesn't exist in database")
    public void test8() {

        var mail = "search@mail.com";

        when(userRepository.findByMail(mail))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.findByMail(mail))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("cannot find user with given mail");

    }

    @Test
    @DisplayName("when mail have wrong format")
    public void test9() {

        var mail = "wrong.mail";

        assertThatThrownBy(() -> usersService.findByMail(mail))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("mail have wrong format");

    }

    @Test
    @DisplayName("when user is searched by username and exists in database")
    public void test10() {

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

        var expectedUserDto = GetUserDto.builder()
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

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        assertThat(usersService.findByUsername(username))
                .isEqualTo(expectedUserDto);

    }

    @Test
    @DisplayName("when user with given username doesn't exist in database")
    public void test11() {

        var username = "username";

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.findByUsername(username))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("cannot find user with given username");

    }

    @Test
    @DisplayName("when username have wrong format")
    public void test12() {

        var username = "user$%name";

        assertThatThrownBy(() -> usersService.findByUsername(username))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("username have wrong format");

    }

    @Test
    @DisplayName("when user is searched by id and exists in database")
    public void test13() {

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

        var expectedUserDto = GetUserDto.builder()
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

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        assertThat(usersService.findById(id))
                .isEqualTo(expectedUserDto);

    }

    @Test
    @DisplayName("when user is searched by id and doesn't exist in db")
    public void test14() {

        var id = 15L;

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.findById(id))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("cannot find user with given id");

    }

    @Test
    @DisplayName("when searched id is null")
    public void test15() {

        assertThatThrownBy(() -> usersService.findById(null))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("id is null");

    }

    @Test
    @DisplayName("when searched id is negative")
    public void test16() {

        assertThatThrownBy(() -> usersService.findById(-5L))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("id is 0 or negative");

    }

    @Test
    @DisplayName("when updating user is correct")
    public void test17() {
        var username = "alfonso123";
        var firstName = "Alfonso";
        var lastName = "Banani";
        var password = "banena12A$";
        var mail = "banani@banani.com";
        var birthDate = LocalDate.now().minusYears(20);
        var gender = Gender.MALE;

        var idToFind = 2L;
        var role = Role.USER_CUSTOMER;

        var userToUpdate = CreateUpdateUserDto.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .passwordConfirmation(password)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        var userFromDb = User.builder()
                .id(idToFind)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .role(role)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        var expectedDto = GetUserDto.builder()
                .id(idToFind)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .role(role)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        when(userRepository.findById(idToFind))
                .thenReturn(Optional.of(User.builder().id(idToFind).role(role).build()));

        when(userRepository.add(any()))
                .thenReturn(Optional.of(userFromDb));

        assertThat(usersService.update(idToFind, userToUpdate))
                .isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when user with given id doesn't exist")
    public void test18() {

        var username = "alfonso123";
        var firstName = "Alfonso";
        var lastName = "Banani";
        var password = "banena12A$";
        var mail = "banani@banani.com";
        var birthDate = LocalDate.now().minusYears(20);
        var gender = Gender.MALE;
        var id = 3L;


        var userToUpdate = CreateUpdateUserDto.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .passwordConfirmation(password)
                .mail(mail)
                .birthDate(birthDate)
                .gender(gender)
                .build();

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());


        assertThatThrownBy(() -> usersService.update(id, userToUpdate))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("user with given id doesn't exist");
    }

    @Test
    @DisplayName("when user is deleted successfully")
    public void test19() {

        var username = "alfonso123";
        var firstName = "Alfonso";
        var lastName = "Banani";
        var password = "banena12A$";
        var mail = "banani@banani.com";
        var birthDate = LocalDate.now().minusYears(20);
        var gender = Gender.MALE;
        var id = 3L;
        var role = Role.USER_CUSTOMER;
        var creationDateTime = LocalDateTime.now().minusDays(20);


        var user = User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .mail(mail)
                .birthDate(birthDate)
                .role(role)
                .creationDateTime(creationDateTime)
                .gender(gender)
                .build();

        var expectedDto = GetUserDto.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .mail(mail)
                .birthDate(birthDate)
                .role(role)
                .creationDateTime(creationDateTime)
                .gender(gender)
                .build();

        when(userRepository.delete(id))
                .thenReturn(Optional.of(user));

        assertThat(usersService.delete(id))
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when user with given id doesn't exist")
    public void test20() {

        var id = 5L;

        when(userRepository.delete(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.delete(id))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("cannot delete user");

    }

    @Test
    @DisplayName("when id is null")
    public void test21() {

        assertThatThrownBy(() -> usersService.delete(null))
                .isInstanceOf(UsersServiceException.class)
                .hasMessageContaining("id is null");

    }

}
