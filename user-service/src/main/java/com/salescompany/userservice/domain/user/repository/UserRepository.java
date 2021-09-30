package com.salescompany.userservice.domain.user.repository;

import com.salescompany.userservice.domain.configs.repository.CrudRepository;
import com.salescompany.userservice.domain.user.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByMail(String mail);

}
