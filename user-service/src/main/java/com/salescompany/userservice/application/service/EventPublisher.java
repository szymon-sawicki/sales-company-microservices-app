package com.salescompany.userservice.application.service;

public interface EventPublisher<T> {
    void publishEvent(T t);
}

