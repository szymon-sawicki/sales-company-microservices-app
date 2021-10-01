package com.salescompany.productservice.infrastructure.controller;

import com.salescompany.productservice.application.service.ProductsService;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/byId")
    public List<GetProductDto> getProductsByIds(@RequestParam String ids) {
        var idss = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return productsService.getProductsByIds(idss);
    }


}
