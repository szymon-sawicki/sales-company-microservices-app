package com.salescompany.userservice.domain.verification_token.repository;

import com.salescompany.userservice.domain.verification_token.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {
    Optional<VerificationToken> findByToken(String token);
}
