package com.salescompany.usersservice.domain.verification_token.repository;

import com.salescompany.usersservice.domain.verification_token.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {
    Optional<VerificationToken> findByToken(String token);
}
