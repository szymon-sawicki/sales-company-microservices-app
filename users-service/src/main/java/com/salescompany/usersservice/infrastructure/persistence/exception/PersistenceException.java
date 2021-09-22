package com.salescompany.usersservice.infrastructure.persistence.exception;

public class PersistenceException extends RuntimeException{
    public PersistenceException(String message) {
        super(message);
    }
}
