package com.salescompany.productservice.infrastructure.controller;

import com.salescompany.productservice.application.service.ProductsService;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.salescompany.productservice.infrastructure.controller.dto.ResponseDataDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")

public class ProductsController {

    private final ProductsService productsService;

    @PostMapping
    public ResponseDataDto<GetProductDto> productCreation(@RequestBody CreateUpdateProductDto createUpdateProductDto) {
        return toResponse(productsService.create(createUpdateProductDto));
    }

    @GetMapping("/{id}")
    public ResponseDataDto<GetProductDto> findProductById(@PathVariable Long id) {
        return toResponse(productsService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseDataDto<GetProductDto> deleteProduct(@PathVariable Long id) {
        return toResponse(productsService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseDataDto<GetProductDto> update(@PathVariable Long id, @RequestBody CreateUpdateProductDto createUpdateProductDto) {
        return toResponse(productsService.update(id, createUpdateProductDto));
    }

    @GetMapping("/ids")
    public ResponseDataDto<List<GetProductDto>> getProductsByIds(@RequestParam String ids) {
        var idsConverted = Arrays.stream(ids.split(",")).map(Long::parseLong).toList();
        return toResponse(productsService.findAllById(idsConverted));
    }

    @GetMapping("/name/{name}")
    public ResponseDataDto<GetProductDto> findProductByName(@PathVariable String name) {
        return toResponse(productsService.findByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseDataDto<List<GetProductDto>> findProductsByCategory(@PathVariable String category) {
        return toResponse(productsService.findAllByCategory(Category.valueOf(category.toUpperCase(Locale.ROOT))));
    }

    @GetMapping("/producer/{id}")
    public ResponseDataDto<List<GetProductDto>> findProductsByProducer(@PathVariable Long id) {
        return toResponse(productsService.findAllByProducer(id));
    }

    @GetMapping("/price")
    public ResponseDataDto<List<GetProductDto>> findProductsInPriceRange(@RequestParam String min, @RequestParam String max) {
        return toResponse(productsService.findAllInPriceRange(new BigDecimal(min), new BigDecimal(max)));
    }



}
