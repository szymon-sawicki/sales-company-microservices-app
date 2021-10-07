package com.salescompany.userservice.infrastructure.persistence.entity;

import com.salescompany.userservice.domain.user.Type.Gender;
import com.salescompany.userservice.domain.user.Type.Role;
import com.salescompany.userservice.domain.user.User;
import com.salescompany.userservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Getter
@Table(name = "users")
@ToString
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String mail;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="creation_date_time")
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
