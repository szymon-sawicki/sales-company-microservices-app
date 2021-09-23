package com.salescompany.usersservice.application.service;

public interface EventPublisher<T> {
    void publishEvent(T t);
}

