package com.salescompany.usersservice.infrastructure.persistence.entity;

import com.salescompany.usersservice.domain.verification_token.VerificationToken;
import com.salescompany.usersservice.infrastructure.persistence.entity.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

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
