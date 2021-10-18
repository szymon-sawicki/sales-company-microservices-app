package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.ProductsServiceException;
import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.dto.GetProducerDto;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.repository.ProductRepository;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
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

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductsServiceTest {

    @TestConfiguration
    public static class ProductsServiceTestConfiguration {

        @MockBean
        public ProducerRepository producerRepository;

        @MockBean
        public ProductRepository productRepository;

        @MockBean
        public WarrantyPolicyRepository warrantyPolicyRepository;

        @Bean
        public ProductsService productsService() {
            return new ProductsService(productRepository, producerRepository, warrantyPolicyRepository);
        }
    }

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarrantyPolicyRepository warrantyPolicyRepository;

    @Autowired
    private ProductsService productsService;

    @Test
    @DisplayName("when product is searched by id - present in db")
    public void test1() {

        var id = 3L;
        var name = "veebra";
        var category = Category.ELECTRONIC;

        var producer = Producer
                .builder()
                .address(Address.builder().build())
                .warrantyPolicies(List.of(WarrantyPolicy.builder().build()))
                .build();

        var product = Product.builder()
                .id(id)
                .name(name)
                .category(category)
                .producer(producer)
                .warrantyPolicy(WarrantyPolicy.builder().build())
                .build();

        var expectedProduct = GetProductDto.builder()
                .id(id)
                .name(name)
                .category(category)
                .producer(producer.toGetProducerDto())
                .warrantyPolicy(GetWarrantyPolicyDto.builder().build())
                .build();


        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        assertThat(productsService.findById(3L))
                .isEqualTo(expectedProduct);

    }

    @Test
    @DisplayName("when product is searched by id - not present in db")
    public void test2() {

        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productsService.findById(3L))
                .isInstanceOf(ProductsServiceException.class)
                .hasMessageContaining("cannot find product by id");

    }

}
