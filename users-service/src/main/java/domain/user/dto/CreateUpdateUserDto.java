package domain.user.dto;

import domain.user.Type.Gender;
import domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
