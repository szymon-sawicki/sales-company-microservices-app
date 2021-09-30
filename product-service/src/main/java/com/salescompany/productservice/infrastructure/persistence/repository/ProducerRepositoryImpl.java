package com.salescompany.productservice.infrastructure.persistence.repository;

import com.salescompany.productservice.domain.producer.Producer;
import com.salescompany.productservice.domain.producer.repository.ProducerRepository;
import com.salescompany.productservice.infrastructure.persistence.dao.ProducerEntityDao;
import com.salescompany.productservice.infrastructure.persistence.entity.ProducerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProducerRepositoryImpl implements ProducerRepository {

    private final ProducerEntityDao producerEntityDao;


    @Override
    public Optional<Producer> add(Producer producer) {
        var insertedProducer = producerEntityDao.save(producer.toEntity());
        return Optional.of(insertedProducer.toProducer());
    }

    @Override
    public Optional<Producer> delete(Long id) {
        var producerToDelete = producerEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("cannot find product to delete"));
        producerEntityDao.delete(producerToDelete);
        return Optional.of(producerToDelete.toProducer());
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerEntityDao.findById(id)
                .map(ProducerEntity::toProducer);
    }

    @Override
    public List<Producer> findAll() {
        return producerEntityDao.findAll()
                .stream()
                .map(ProducerEntity::toProducer)
                .toList();
    }

    @Override
    public List<Producer> findAllById(List<Long> ids) {
        return producerEntityDao.findAllById(ids)
                .stream()
                .map(ProducerEntity::toProducer)
                .toList();
    }

    @Override
    public Optional<Producer> findByName(String name) {
        return producerEntityDao.findByName(name)
                .map(ProducerEntity::toProducer);
    }
}
