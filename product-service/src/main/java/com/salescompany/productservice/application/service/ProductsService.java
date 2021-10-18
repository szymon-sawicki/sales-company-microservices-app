package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.AddressesServiceException;
import com.salescompany.productservice.application.service.exception.ProducersServiceException;
import com.salescompany.productservice.application.service.exception.ProductsServiceException;
import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.dto.validator.CreateUpdateProductDtoValidator;
import com.salescompany.productservice.domain.product.repository.ProductRepository;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
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
    private final WarrantyPolicyRepository warrantyPolicyRepository;

    public GetProductDto findById(Long id) {
        if(id == null) {
            throw new ProductsServiceException("id is null");
        }
        if(id <= 0) {
            throw new ProductsServiceException("id is 0 or negative");
        }
        return productRepository.findById(id)
                .map(Product::toGetProductDto)
                .orElseThrow(() -> new ProductsServiceException("cannot find product by id"));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetProductDto create(CreateUpdateProductDto createUpdateProductDto) {
        Validator.validate(new CreateUpdateProductDtoValidator(),createUpdateProductDto);

        var producerFromDb = producerRepository.findById(createUpdateProductDto.getProducerId())
                .orElseThrow(()->new ProductsServiceException("cannot find producer"));

        var warrantyPolicyFromDb = warrantyPolicyRepository.findById(createUpdateProductDto.getWarrantyPolicyId())
                .orElseThrow(() -> new ProductsServiceException("cannot find warranty policy"));

        var productToInsert = createUpdateProductDto.toProduct().withProducer(producerFromDb).withWarrantyPolicy(warrantyPolicyFromDb);

        return productRepository.add(productToInsert)
                .map(Product::toGetProductDto)
                .orElseThrow(() -> new ProductsServiceException("cannot add new product"));

    }

    public List<GetProductDto> findAllById(List<Long> ids) {
        if(ids == null)  {
            throw new ProductsServiceException("list of ids is null");
        }

        if(ids.isEmpty()) {
            throw new ProductsServiceException("list of ids is empty");
        }

        return productRepository.findAllById(ids)
                .stream()
                .map(Product::toGetProductDto)
                .toList();
    }




}
