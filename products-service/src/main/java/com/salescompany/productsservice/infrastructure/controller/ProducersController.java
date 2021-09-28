package com.salescompany.productsservice.infrastructure.controller;

import com.salescompany.productsservice.application.service.ProducersService;
import com.salescompany.productsservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")

public class ProducersController {

    private final ProducersService producersService;

    @PostMapping
    ResponseDataDto<GetProducerDto> producerCreation(@RequestBody CreateUpdateProducerDto createUpdateProducerDto) {
        return ResponseDataDto.toResponse(producersService.createProducer(createUpdateProducerDto));
    }

    @GetMapping("/{id}")
    ResponseDataDto<GetProducerDto> findProducerById(@PathVariable Long id) {
        return ResponseDataDto.toResponse(producersService.findById(id));
    }

}
