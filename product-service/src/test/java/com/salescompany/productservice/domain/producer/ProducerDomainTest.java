package com.salescompany.productservice.domain.producer;

import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.type.Industry;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProducerDomainTest {

    @Test
    @DisplayName("when producer has new address")
    public void test1() {

        var id = 5L;
        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var address = Address.builder().build();
        var warrantyPolicies = List.of(WarrantyPolicy.builder().build());

        var newAddress = Address.builder().city("Posen").street("Garbary").build();

        var producer = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedProducer = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(newAddress)
                .warrantyPolicies(warrantyPolicies)
                .build();

        assertThat(producer.withAddress(newAddress))
                .isEqualTo(expectedProducer);

    }

    @Test
    @DisplayName("when conversion to get producer dto is correct")
    public void test2() {

        var id = 5L;
        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var address = Address.builder().build();
        var warrantyPolicies = List.of(WarrantyPolicy.builder().build());

        var newAddress = Address.builder().city("Posen").street("Garbary").build();

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

        assertThat(producer.toGetProducerDto())
                .isEqualTo(expectedDto);


    }

    @Test
    @DisplayName("when conversion to entity is correct")
    public void test3() {

        var id = 5L;
        var name = "Emontix";
        var industry = Industry.ELECTRONIC;
        var address = Address.builder().build();
        var warrantyPolicies = List.of(WarrantyPolicy.builder().build());

        var newAddress = Address.builder().city("Posen").street("Garbary").build();

        var producer = Producer.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .address(address)
                .warrantyPolicies(warrantyPolicies)
                .build();

        var expectedEntity = ProducerEntity.builder()
                .id(id)
                .name(name)
                .industry(industry)
                .addressEntity(address.toEntity())
                .warrantyPolicies(warrantyPolicies.stream().map(WarrantyPolicy::toEntity).toList())
                .build();

        assertThat(producer.toEntity())
                .isEqualTo(expectedEntity);


    }




}
