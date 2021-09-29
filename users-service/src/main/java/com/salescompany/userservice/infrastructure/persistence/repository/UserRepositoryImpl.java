package com.salescompany.userservice.infrastructure.persistence.repository;

import com.salescompany.userservice.domain.user.User;
import com.salescompany.userservice.domain.user.repository.UserRepository;
import com.salescompany.userservice.infrastructure.persistence.dao.UserEntityDao;
import com.salescompany.userservice.infrastructure.persistence.entity.UserEntity;
import com.salescompany.userservice.infrastructure.persistence.exception.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityDao userEntityDao;

    @Override
    public Optional<User> findByUsername(String username) {
        return userEntityDao.findByUsername(username)
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return userEntityDao.findByMail(mail)
                .map(UserEntity::toUser);
    }

    @Override
    public Optional<User> delete(Long id) {
        var userToDelete = userEntityDao.findById(id)
                .map(UserEntity::toUser)
                .orElseThrow(() -> new PersistenceException("cannot find user to delete"));

        userEntityDao.deleteById(id);

        return Optional.of(userToDelete);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityDao.findById(id)
                .map(UserEntity::toUser);
    }

    @Override
    public List<User> findAll() {
        return userEntityDao.findAll().stream()
                .map(UserEntity::toUser)
                .toList();
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return userEntityDao.findAllById(ids).stream()
                .map(UserEntity::toUser)
                .toList();
    }

    @Override
    public Optional<User> add(User user) {
        return Optional.of(userEntityDao.save(user.toEntity()))
                .map(UserEntity::toUser);
    }

}
