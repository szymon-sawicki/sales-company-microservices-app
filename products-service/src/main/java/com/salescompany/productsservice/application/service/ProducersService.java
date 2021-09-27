package com.salescompany.productsservice.application.service;

import com.salescompany.productsservice.application.service.exception.AddressesServiceException;
import com.salescompany.productsservice.application.service.exception.ProducersServiceException;
import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.producer.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducersService {

    private final ProducerRepository producerRepository;

    public GetProducerDto findById(Long id) {
        if(id == null) {
            throw new AddressesServiceException("id is null");
        }
        if(id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }
        return producerRepository.findById(id)
                .map(Producer::toGetProducerDto)
                .orElseThrow(() -> new ProducersServiceException("cannot find producer"));
    }

}
