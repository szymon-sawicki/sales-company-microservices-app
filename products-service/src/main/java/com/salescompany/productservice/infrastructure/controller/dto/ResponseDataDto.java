package com.salescompany.productservice.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDataDto<T> {
    private T data;

    @Builder.Default
    private String error = "";

    public static <T> ResponseDataDto<T> toResponse(T t) {
        return ResponseDataDto
                .<T>builder()
                .data(t)
                .build();
    }

    public static ResponseDataDto<?> toError(String errorMessage) {
        return ResponseDataDto
                .builder()
                .error(errorMessage)
                .build();
    }

}
