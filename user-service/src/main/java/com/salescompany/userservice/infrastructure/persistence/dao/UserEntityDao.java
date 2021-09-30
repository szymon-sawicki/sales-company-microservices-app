package com.salescompany.userservice.infrastructure.persistence.dao;

import com.salescompany.userservice.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityDao extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByMail(String mail);
}
