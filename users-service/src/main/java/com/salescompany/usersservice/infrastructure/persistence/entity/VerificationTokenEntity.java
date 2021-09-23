package com.salescompany.usersservice.infrastructure.persistence.entity;

import com.salescompany.usersservice.domain.verification_token.VerificationToken;
import com.salescompany.usersservice.infrastructure.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity extends BaseEntity {
    private String token;
    private LocalDateTime dateTime;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private UserEntity user;

    public VerificationToken toVerificationToken() {
        return VerificationToken
                .builder()
                .id(id)
                .token(token)
                .dateTime(dateTime)
                .user(user.toUser())
                .build();
    }
}
