package domain.user.dto;

import domain.user.Type.Gender;
import domain.user.Type.Role;
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
public class GetUserDto {
    private Long id;
    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private Role role;
    private LocalDateTime creationDateTime;
    boolean enabled;
}
