package com.salescompany.productsservice.infrastructure.controller;

import com.salescompany.productsservice.application.service.ProductsService;
import com.salescompany.productsservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productsservice.domain.product.dto.GetProductDto;
import com.salescompany.productsservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")

public class ProductsController {

    private final ProductsService productsService;

    @PostMapping
    public ResponseDataDto<GetProductDto> productCreation(@RequestBody CreateUpdateProductDto createUpdateProductDto) {
        return ResponseDataDto.toResponse(productsService.create(createUpdateProductDto));
    }

    @GetMapping("/{id}")
    public ResponseDataDto<GetProductDto> getProductById(@PathVariable Long id) {
        return ResponseDataDto.toResponse(productsService.findById(id));
    }

}
