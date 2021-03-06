package com.salescompany.productservice.infrastructure.persistence.repository;

import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.product.Product;
import com.salescompany.productservice.domain.product.repository.ProductRepository;
import com.salescompany.productservice.domain.product.type.Category;
import com.salescompany.productservice.infrastructure.persistence.dao.ProductEntityDao;
import com.salescompany.productservice.infrastructure.persistence.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductEntityDao productEntityDao;

    @Override
    public Optional<Product> add(Product product) {
        var insertedProduct = productEntityDao.save(product.toEntity());
        return Optional.of(insertedProduct.toProduct());
    }

    @Override
    public Optional<Product> delete(Long id) {
        var productToDelete = productEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("cannot find product to delete"));
        productEntityDao.delete(productToDelete);
        return Optional.of(productToDelete.toProduct());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productEntityDao.findById(id)
                .map(ProductEntity::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return productEntityDao.findAll()
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAllById(List<Long> ids) {
        return productEntityDao.findAllById(ids)
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAllByProducer(Producer producer) {
        return productEntityDao.findAllByProducer(producer.toEntity())
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productEntityDao.findAllByCategory(category)
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max) {
        return productEntityDao.findAllByPriceBetween(min,max)
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productEntityDao.findByName(name)
                .map(ProductEntity::toProduct);
    }
}

