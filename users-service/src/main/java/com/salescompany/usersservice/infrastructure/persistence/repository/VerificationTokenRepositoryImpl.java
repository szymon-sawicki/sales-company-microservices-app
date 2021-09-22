package com.salescompany.usersservice.infrastructure.persistence.repository;

import com.salescompany.usersservice.domain.verification_token.VerificationToken;
import com.salescompany.usersservice.domain.verification_token.repository.VerificationTokenRepository;
import com.salescompany.usersservice.infrastructure.persistence.dao.VerificationTokenEntityDao;
import com.salescompany.usersservice.infrastructure.persistence.entity.VerificationTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    private final VerificationTokenEntityDao verificationTokenEntityDao;

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenEntityDao.findByToken(token)
                .map(VerificationTokenEntity::toVerificationToken);
    }


}
