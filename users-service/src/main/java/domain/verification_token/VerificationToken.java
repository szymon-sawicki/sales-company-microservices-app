package domain.verification_token;

import domain.user.User;
import domain.user.UserUtils;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class VerificationToken {
    Long id;
    String token;
    LocalDateTime dateTime;
    User user;

    public boolean isValid() { return LocalDateTime.now().isBefore(dateTime); }

    public Long getUserId() { return UserUtils.toId.apply(user); }


    }

