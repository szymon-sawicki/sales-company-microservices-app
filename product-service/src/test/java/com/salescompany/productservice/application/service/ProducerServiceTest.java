package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.ProducersServiceException;
import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.CreateUpdateAddressDto;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.address.repository.AddressRepository;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.CreateUpdateWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
import com.salescompany.productservice.domain.warranty_policy.type.ServiceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProducerServiceTest {

    @TestConfiguration
    public static class ProducersServiceTestConfiguration {

        @MockBean
        public ProducerRepository producerRepository;

        @MockBean
        public AddressRepository addressRepository;

        @MockBean
        public WarrantyPolicyRepository warrantyPolicyRepository;

        @Bean
        public ProducersService producersService() {
            return new ProducersService(producerRepository, addressRepository);
        }
    }


    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private WarrantyPolicyRepository warrantyPolicyRepository;

    @Autowired
    private ProducersService producersService;


    @Test
    @DisplayName("when producer is searched by id")
    public void test1() {

        var id = 5L;
        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var address = Address.builder().build();
        var warrantyPolicies = List.of(WarrantyPolicy.builder().build());

        var producer = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedDto = GetProducerDto.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address.toGetAddressDto())
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toGetWarrantyPolicyDto).toList())
                .build();

        when(producerRepository.findById(id))
                .thenReturn(Optional.of(producer));

        assertThat(producersService.findById(5L))
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when producer is searched by name and exists")
    public void test2() {

        var id = 5L;
        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var address = Address.builder().build();
        var warrantyPolicies = List.of(WarrantyPolicy.builder().build());

        var producer = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedDto = GetProducerDto.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address.toGetAddressDto())
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toGetWarrantyPolicyDto).toList())
                .build();

        when(producerRepository.findByName(name))
                .thenReturn(Optional.of(producer));

        assertThat(producersService.findByName("Emontix"))
                .isEqualTo(expectedDto);

    }

    @Test
    @DisplayName("when producer with given name already exists in db")
    public void test3() {

        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var warrantyPolicies = List.of(CreateUpdateWarrantyPolicyDto.builder()
                .possibleServices(List.of(ServiceType.EXCHANGE))
                .processingPeriod(34)
                .warrantyPeriod(24)
                .returningPercent(70)
                .build());

        var address = CreateUpdateAddressDto.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();


        var producerDto = CreateUpdateProducerDto.builder()
                .name(name)
                .industry(industry)
                .createUpdateAddressDto(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        when(addressRepository.findByAddress(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(address.toAddress()));

        when(producerRepository.findByName(name))
                .thenReturn(Optional.of(Producer.builder()
                        .address(Address.builder().build())
                        .warrantyPolicies(List.of(WarrantyPolicy.builder().build()))
                        .build()));

        assertThatThrownBy(() -> producersService.createProducer(producerDto))
                .isInstanceOf(ProducersServiceException.class)
                .hasMessageContaining("producer with given name already exists in db");

    }

    @Test
    @DisplayName("when producer is created successfully")
    public void test4() {

        var name = "Emontix";
        var industry = Industry.ELECTRONIC;


        var warrantyPolicies = List.of(CreateUpdateWarrantyPolicyDto.builder()
                .possibleServices(List.of(ServiceType.EXCHANGE))
                .processingPeriod(34)
                .warrantyPeriod(24)
                .returningPercent(70)
                .build());

        var address = CreateUpdateAddressDto.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();


        var producerDto = CreateUpdateProducerDto.builder()
                .name(name)
                .industry(industry)
                .createUpdateAddressDto(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedProducer = Producer.builder()
                .id(4L)
                .name(name)
                .address(address.toAddress())
                .warrantyPolicies(warrantyPolicies.stream().map(CreateUpdateWarrantyPolicyDto::toWarrantyPolicy).toList())
                .industry(industry)
                .build();

        when(addressRepository.findByAddress(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(address.toAddress()));

        when(producerRepository.findByName(name))
                .thenReturn(Optional.empty());

        when(producerRepository.add(any()))
                .thenReturn(Optional.of(expectedProducer));

        assertThat(producersService.createProducer(producerDto).getId())
                .isEqualTo(4L);

    }

    @Test
    @DisplayName("when producer is searched by username and don't exists")
    public void test5() {

        when(producerRepository.findByName(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> producersService.findByName("aaaeed"))
                .isInstanceOf(ProducersServiceException.class)
                .hasMessageContaining("cannot find producer by name");

    }

    @Test
    @DisplayName("when producer is deleted successfully")
    public void test6() {

        var name = "Ekofinix";
        var industry = Industry.ELECTRONIC;

        var warrantyPolicies = List.of(WarrantyPolicy.builder()
                .possibleServices(List.of(ServiceType.EXCHANGE))
                .processingPeriod(34)
                .warrantyPeriod(24)
                .returningPercent(70)
                .build());


        var address = Address.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();

        var producer = Producer.builder()
                .id(1L)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedDto = GetProducerDto.builder()
                .id(1L)
                .name(name)
                .industry(industry)
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toGetWarrantyPolicyDto).toList())
                .address(address.toGetAddressDto())
                .build();



        when(producerRepository.delete(1L))
                .thenReturn(Optional.of(producer));

        assertThat(producersService.delete(1L))
                .isEqualTo(expectedDto);
    }

    @Test
    @DisplayName("when cannot find producer to update")
    public void test7() {

        var name = "Ekofinix";
        var industry = Industry.ELECTRONIC;

        var warrantyPolicies = List.of(CreateUpdateWarrantyPolicyDto.builder()
                .possibleServices(List.of(ServiceType.EXCHANGE))
                .processingPeriod(34)
                .warrantyPeriod(24)
                .returningPercent(70)
                .build());


        var address = CreateUpdateAddressDto.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();

        var producer = CreateUpdateProducerDto.builder()
                .name(name)
                .industry(industry)
                .createUpdateAddressDto(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var id = 3L;

        when(producerRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> producersService.update(id,producer))
                .isInstanceOf(ProducersServiceException.class)
                .hasMessageContaining("cannot find producer to update");

    }

    @Test
    @DisplayName("when producer is updated successfully")
    public void test8() {

        var name = "Ekofinix";
        var industry = Industry.ELECTRONIC;

        var warrantyPolicies = List.of(CreateUpdateWarrantyPolicyDto.builder()
                .possibleServices(List.of(ServiceType.EXCHANGE))
                .processingPeriod(34)
                .warrantyPeriod(24)
                .returningPercent(70)
                .build());


        var address = CreateUpdateAddressDto.builder()
                .zipCode("956-95")
                .city("Prague")
                .street("Main Street")
                .houseNumber("2345")
                .build();

        var producerUpdate = CreateUpdateProducerDto.builder()
                .name(name)
                .industry(industry)
                .createUpdateAddressDto(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var id = 7L;

        var producerFromDb = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address.toAddress())
                .warrantyPolicies(warrantyPolicies.stream().map(CreateUpdateWarrantyPolicyDto::toWarrantyPolicy).toList())
                .build();



        var expectedProducer = GetProducerDto.builder()
                .id(id)
                .name(name)
                .address(address.toAddress().toGetAddressDto())
                .warrantyPolicies(warrantyPolicies.stream().map(policy->policy
                        .toWarrantyPolicy()
                        .toGetWarrantyPolicyDto())
                        .toList())
                .industry(industry)
                .build();

        when(producerRepository.findById(id))
                .thenReturn(Optional.of(producerFromDb));

        when(producerRepository.add(any()))
                .thenReturn(Optional.of(producerFromDb));

        assertThat(producersService.update(7L,producerUpdate))
                .isEqualTo(expectedProducer);

    }


}
