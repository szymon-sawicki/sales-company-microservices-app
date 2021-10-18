package com.salescompany.productservice.infrastructure.controller;

import com.salescompany.productservice.application.service.ProducersService;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.infrastructure.controller.dto.ResponseDataDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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

    @GetMapping("/name/{name}")
    ResponseDataDto<GetProducerDto> findProducerByName(@PathVariable String name) {
        return ResponseDataDto.toResponse(producersService.findByName(name));
    }

    @PutMapping ("/{id}")
    ResponseDataDto<GetProducerDto> updateProducer(@PathVariable Long id, @RequestBody CreateUpdateProducerDto createUpdateProducerDto) {
        return ResponseDataDto.toResponse(producersService.update(id,createUpdateProducerDto));
    }

    @DeleteMapping("/{id}")
    ResponseDataDto<GetProducerDto> deleteProducer(@PathVariable Long id) {
        return ResponseDataDto.toResponse(producersService.delete(id));
    }

    @GetMapping("/all")
    ResponseDataDto<List<GetProducerDto>> findAllProducers() {
        return ResponseDataDto.toResponse(producersService.findAll());
    }


    @GetMapping("/industry/{industry}")
    ResponseDataDto<List<GetProducerDto>> findAllByIndustry(@PathVariable String industry) {
        return ResponseDataDto.toResponse(producersService.findAllByIndustry(Industry.valueOf(industry.toUpperCase(Locale.ROOT))));
    }
 }
