package com.salescompany.userservice.domain.user;

import com.salescompany.userservice.domain.user.Type.Gender;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.dto.CreateUserResponseDto;
import com.salescompany.userservice.domain.user.dto.GetUserDto;
import com.salescompany.userservice.infrastructure.persistence.entity.UserEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@EqualsAndHashCode

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

    public void activate() {
        enabled=true;
    }

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

    public User withRole(Role newRole) {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .role(newRole)
                .creationDateTime(creationDateTime)
                .build();
    }

    public User withId(Long newId) {
        return User.builder()
                .id(newId)
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

    public UserEntity toEntity() {
        return UserEntity.builder()
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
}
