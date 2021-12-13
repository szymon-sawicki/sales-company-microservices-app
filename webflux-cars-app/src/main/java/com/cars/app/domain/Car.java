package com.cars.app.domain;

import lombok.Builder;

@Builder
public record Car (String model, Engine engine, Integer mileage) {}