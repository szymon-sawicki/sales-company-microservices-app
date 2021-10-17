package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.AddressesServiceException;
import com.salescompany.productservice.application.service.exception.ProducersServiceException;
import com.salescompany.productservice.domain.address.repository.AddressRepository;
import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.dto.validator.CreateUpdateProducerDtoValidator;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.dto.CreateUpdateWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProducersService {

    // TODO finding producers by category
    // TODO find all producers

    private final ProducerRepository producerRepository;
    private final AddressRepository addressRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetProducerDto createProducer(CreateUpdateProducerDto createUpdateProducerDto) {

        Validator.validate(new CreateUpdateProducerDtoValidator(), createUpdateProducerDto);

        var addressDto = createUpdateProducerDto.getCreateUpdateAddressDto();

        var addressFromDb = addressRepository
                .findByAddress(addressDto.getStreet(), addressDto.getHouseNumber(), addressDto.getCity(), addressDto.getZipCode())
                .orElseGet(() -> addressRepository
                        .add(addressDto.toAddress())
                        .orElseThrow(() -> new ProducersServiceException("cannot add address")));

        if (producerRepository.findByName(createUpdateProducerDto.getName()).isPresent()) {
            throw new ProducersServiceException("producer with given name already exists in db");
        }


        var warrantyPolicies = createUpdateProducerDto.getWarrantyPolicies()
                .stream()
                .map(CreateUpdateWarrantyPolicyDto::toWarrantyPolicy)
                .toList();

        var producerToInsert = createUpdateProducerDto.toProducer().withAddress(addressFromDb).withWarrantyPolicies(warrantyPolicies);

        return producerRepository.add(producerToInsert)
                .map(Producer::toGetProducerDto)
                .orElseThrow(() -> new ProducersServiceException("cannot add producer"));

    }


    public GetProducerDto findById(Long id) {
        if (id == null) {
            throw new AddressesServiceException("id is null");
        }
        if (id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }
        return producerRepository.findById(id)
                .orElseThrow(() -> new ProducersServiceException("cannot find producer"))
                .toGetProducerDto();
    }

    public GetProducerDto delete(Long id) {

        if (id == null) {
            throw new AddressesServiceException("id is null");
        }
        if (id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }

        return producerRepository.delete(id)
                .orElseThrow(() -> new ProducersServiceException("cannot delete producer"))
                .toGetProducerDto();
    }

    public GetProducerDto update(Long id, CreateUpdateProducerDto createUpdateProducerDto) {
        if (id == null) {
            throw new AddressesServiceException("id is null");
        }
        if (id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }

        Validator.validate(new CreateUpdateProducerDtoValidator(),createUpdateProducerDto);

        if(producerRepository.findById(id).isEmpty()) {
            throw new ProducersServiceException("cannot find producer to update");
        }

        return producerRepository.add(createUpdateProducerDto.toProducer().withId(id))
                .orElseThrow(() -> new ProducersServiceException("cannot update producer"))
                .toGetProducerDto();
    }

    public GetProducerDto findByName(String name) {
        if (name == null) {
            throw new AddressesServiceException("name is null");
        }
        if (!name.matches("[\\w\\s\\-'.]{3,20}+")) {
            throw new ProducersServiceException("name have wrong format");
        }
        return producerRepository.findByName(name)
                .orElseThrow(() -> new ProducersServiceException("cannot find producer by name"))
                .toGetProducerDto();
    }

    public List<GetProducerDto> findAll() {
        return producerRepository.findAll()
                .stream()
                .map(Producer::toGetProducerDto)
                .toList();
    }

    public List<GetProducerDto> findAllByIndustry(Industry industry) {
        if(industry == null) {
            throw new ProducersServiceException("industry is null");
        }

        return producerRepository.findAllByIndustry(industry)
                .stream()
                .map(Producer::toGetProducerDto)
                .toList();
    }

}
