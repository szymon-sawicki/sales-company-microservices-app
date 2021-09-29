package com.salescompany.productservice.infrastructure.controller;

import com.salescompany.productservice.application.service.ProducersService;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.infrastructure.controller.dto.ResponseDataDto;
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
