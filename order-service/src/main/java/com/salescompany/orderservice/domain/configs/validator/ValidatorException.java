package com.salescompany.orderservice.domain.configs.validator;

public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }
}
