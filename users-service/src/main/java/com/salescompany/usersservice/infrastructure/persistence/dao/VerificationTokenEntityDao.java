package com.salescompany.usersservice.infrastructure.persistence.dao;

import com.salescompany.usersservice.infrastructure.persistence.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenEntityDao extends JpaRepository<VerificationTokenEntity, Long> {
    Optional<VerificationTokenEntity> findByToken(String token);
}
