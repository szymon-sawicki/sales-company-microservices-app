package com.salescompany.usersservice.domain.configs.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> add(T t);
    Optional<T> delete(ID id);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAllById(List<ID> ids);

}

