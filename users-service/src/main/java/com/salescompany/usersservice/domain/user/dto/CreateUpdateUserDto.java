package com.salescompany.usersservice.domain.user.dto;

import com.salescompany.usersservice.domain.user.Type.Gender;
import com.salescompany.usersservice.domain.user.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CreateUpdateUserDto {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String mail;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDateTime creationDateTime;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .mail(mail)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .creationDateTime(creationDateTime)
                .build();
    }
}
