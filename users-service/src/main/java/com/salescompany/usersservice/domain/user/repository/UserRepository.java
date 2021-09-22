package com.salescompany.usersservice.domain.user.repository;

import com.salescompany.usersservice.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByMail(String mail);

}
