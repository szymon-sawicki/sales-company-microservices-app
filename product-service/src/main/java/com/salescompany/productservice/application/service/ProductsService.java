package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.ProductsServiceException;
import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.product.dto.GetProductDto;
import com.salescompany.productservice.domain.product.dto.validator.CreateUpdateProductDtoValidator;
import com.salescompany.productservice.domain.product.repository.ProductRepository;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    public GetProductDto findByName(String name) {
        if(name == null)  {
            throw new ProductsServiceException("name is null");
        }

        return productRepository.findByName(name)
                .orElseThrow(() -> new ProductsServiceException("cannot find product"))
                .toGetProductDto();
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetProductDto delete(Long id) {
        if (id == null) {
            throw new ProductsServiceException("id is null");
        }
        if (id <= 0) {
            throw new ProductsServiceException("id is 0 or negative");
        }

        return productRepository.delete(id)
                .orElseThrow(() -> new ProductsServiceException("cannot delete product"))
                .toGetProductDto();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetProductDto update(Long id, CreateUpdateProductDto createUpdateProductDto) {
        if (id == null) {
            throw new ProductsServiceException("id is null");
        }
        if (id <= 0) {
            throw new ProductsServiceException("id is 0 or negative");
        }

        if(productRepository.findById(id).isEmpty()) {
                throw new ProductsServiceException("cannot find product to update");

        }

        Validator.validate(new CreateUpdateProductDtoValidator(),createUpdateProductDto);

        var producerFromDb = producerRepository.findById(createUpdateProductDto.getProducerId())
                .orElseThrow(()->new ProductsServiceException("cannot find producer"));

        var warrantyPolicyFromDb = warrantyPolicyRepository.findById(createUpdateProductDto.getWarrantyPolicyId())
                .orElseThrow(() -> new ProductsServiceException("cannot find warranty policy"));

        var productToUpdate = createUpdateProductDto
                .toProduct()
                .withProducer(producerFromDb)
                .withWarrantyPolicy(warrantyPolicyFromDb)
                .withId(id);

        return productRepository.add(productToUpdate)
                .orElseThrow(() -> new ProductsServiceException("cannot update product"))
                .toGetProductDto();
    }

    public List<GetProductDto> findAllInPriceRange(BigDecimal min, BigDecimal max) {
        if(min == null) {
            throw new ProductsServiceException("min range is null");
        }
        if(max == null) {
            throw new ProductsServiceException("max range is null");
        }
        if(min.compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductsServiceException("min value is out of range");
        }
        if(max.compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductsServiceException("max value is out of range");
        }
        if(min.compareTo(max) > 0) {
            throw new ProductsServiceException("range is incorrect");
        }

        return productRepository.findAllByPriceBetween(min,max)
                .stream()
                .map(Product::toGetProductDto)
                .toList();
    }

    List<GetProductDto> findAllByCategory(Category category) {
        if(category == null) {
            throw new ProductsServiceException("category is null");
        }

        return productRepository.findAllByCategory(category)
                .stream()
                .map(Product::toGetProductDto)
                .toList();
    }

    List<GetProductDto> findAllByProducer(Producer producer) {
        if(producer == null) {
            throw new ProductsServiceException("producer is null");
        }

        return productRepository.findAllByProducer(producer)
                .stream()
                .map(Product::toGetProductDto)
                .toList();
    }




}
