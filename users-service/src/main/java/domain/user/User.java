package domain.user;

import domain.user.Type.Gender;
import domain.user.Type.Role;
import domain.user.dto.CreateUserResponseDto;
import domain.user.dto.GetUserDto;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class User {
    Long id;
    String username;
    String password;
    String mail;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthDate;
    Role role;
    LocalDateTime creationDateTime;
    boolean enabled;

    public GetUserDto toGetUserDto() {
        return GetUserDto.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .role(role)
                .creationDateTime(creationDateTime)
                .build();
    }

    public CreateUserResponseDto toCreateUserResponseDto() {
        return CreateUserResponseDto.builder()
                .id(id)
                .username(username)
                .build();
    }

    public User withCreationDateTime(LocalDateTime newTime) {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .role(role)
                .creationDateTime(newTime)
                .build();
    }
    public User withPassword(String newPassword) {
        return User.builder()
                .id(id)
                .username(username)
                .password(newPassword)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .role(role)
                .creationDateTime(creationDateTime)
                .build();
    }
}
