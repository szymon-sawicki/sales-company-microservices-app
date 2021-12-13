package com.cars.app.domain;

import lombok.Builder;

@Builder
public record Engine(Integer horsePower, Fuel fuel) {
}
