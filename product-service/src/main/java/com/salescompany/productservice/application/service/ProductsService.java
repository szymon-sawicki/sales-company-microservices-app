package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.AddressesServiceException;
import com.salescompany.productservice.application.service.exception.ProducersServiceException;
import com.salescompany.productservice.application.service.exception.ProductsServiceException;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetProductDto create(CreateUpdateProductDto createUpdateProductDto) {
        // TODO validation

        var producerFromDb = producerRepository.findById(createUpdateProductDto.getProducerId())
                .orElseThrow(()->new ProductsServiceException("cannot find producer"));

        var productToInsert = createUpdateProductDto.toProduct().withProducer(producerFromDb);

        return productRepository.add(productToInsert)
                .map(Product::toGetProductDto)
                .orElseThrow(() -> new ProductsServiceException("cannot add new product"));

    }

    public List<GetProductDto> getProductsByIds(List<Long> ids) {

        return productRepository.findAllById(ids)
                .stream().map(Product::toGetProductDto)
                .toList();

    }



}
