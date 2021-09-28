package com.salescompany.productsservice.application.service;

import com.salescompany.productsservice.application.service.exception.AddressesServiceException;
import com.salescompany.productsservice.application.service.exception.ProducersServiceException;
import com.salescompany.productsservice.application.service.exception.ProductsServiceException;
import com.salescompany.productsservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productsservice.domain.product.Product;
import com.salescompany.productsservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productsservice.domain.product.dto.GetProductDto;
import com.salescompany.productsservice.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    public GetProductDto findById(Long id) {
        if(id == null) {
            throw new AddressesServiceException("id is null");
        }
        if(id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }
        return productRepository.findById(id)
                .map(Product::toGetProductDto)
                .orElseThrow(() -> new ProducersServiceException("cannot find product by id"));
    }

    public GetProductDto create(CreateUpdateProductDto createUpdateProductDto) {
        // TODO validation

        var producerFromDb = producerRepository.findById(createUpdateProductDto.getProducerId())
                .orElseThrow(()->new ProductsServiceException("cannot find producer"));

        var productToInsert = createUpdateProductDto.toProduct().withProducer(producerFromDb);

        return productRepository.add(productToInsert)
                .map(Product::toGetProductDto)
                .orElseThrow(() -> new ProductsServiceException("cannot add new product"));

    }



}
