package com.salescompany.productsservice.application.service;

import com.salescompany.productsservice.application.service.exception.AddressesServiceException;
import com.salescompany.productsservice.application.service.exception.ProducersServiceException;
import com.salescompany.productsservice.domain.address.repository.AddressRepository;
import com.salescompany.productsservice.domain.producer.Producer;
import com.salescompany.productsservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productsservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productsservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productsservice.infrastructure.persistence.dao.AddressEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducersService {

    private final ProducerRepository producerRepository;
    private final AddressRepository addressRepository;

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


    public GetProducerDto createProducer(CreateUpdateProducerDto createUpdateProducerDto) {
        if(createUpdateProducerDto == null) {
            throw new ProducersServiceException("create update producer dto is null");
        }

        // TODO validation

        var addressDto = createUpdateProducerDto.getCreateUpdateAddressDto();

        var addressFromDb = addressRepository
                .findByAddress(addressDto.getStreet(),addressDto.getHouseNumber(),addressDto.getCity(),addressDto.getZipCode())
                .orElseGet(()->addressRepository
                        .add(addressDto.toAddress())
                        .orElseThrow(() -> new ProducersServiceException("cannot add address")));

        if(producerRepository.findByName(createUpdateProducerDto.getName()).isPresent()) {
            throw new ProducersServiceException("producer with given name already exists in db");
        }

        var producerToInsert = createUpdateProducerDto.toProducer().withAddress(addressFromDb);

        return producerRepository.add(producerToInsert)
                .map(Producer::toGetProducerDto)
                .orElseThrow(()-> new ProducersServiceException("cannot add user"));

    }

}
