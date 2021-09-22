package com.salescompany.usersservice.infrastructure.persistence.entity;

import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.Type.Role;
import com.salescompany.usersservice.domain.user.User;
import com.salescompany.usersservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDateTime creationDateTime;
    boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User toUser() {
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
                .creationDateTime(creationDateTime)
                .build();
    }

}
