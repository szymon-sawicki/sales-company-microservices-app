package com.salescompany.userservice.domain.verification_token;

import com.salescompany.userservice.domain.user.User;
import com.salescompany.userservice.domain.user.UserUtils;
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

