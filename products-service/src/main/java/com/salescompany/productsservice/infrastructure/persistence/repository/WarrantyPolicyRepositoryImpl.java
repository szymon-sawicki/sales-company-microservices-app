package com.salescompany.productsservice.infrastructure.persistence.repository;

import com.salescompany.productsservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productsservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
import com.salescompany.productsservice.infrastructure.persistence.dao.WarrantyPolicyEntityDao;
import com.salescompany.productsservice.infrastructure.persistence.entity.WarrantyPolicyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class WarrantyPolicyRepositoryImpl implements WarrantyPolicyRepository {

    private final WarrantyPolicyEntityDao warrantyPolicyEntityDao;

    @Override
    public Optional<WarrantyPolicy> add(WarrantyPolicy warrantyPolicy) {
        var insertedWarrantyPolicy = warrantyPolicyEntityDao.save(warrantyPolicy.toEntity());
        return Optional.of(insertedWarrantyPolicy.toWarrantyPolicy());
    }

    @Override
    public Optional<WarrantyPolicy> delete(Long id) {
        var warrantyPolicyToDelete = warrantyPolicyEntityDao.findById(id)
                .orElseThrow(() -> new PersistenceException("cannot find warranty policy to delete"));
        return Optional.of(warrantyPolicyToDelete.toWarrantyPolicy());
    }

    @Override
    public Optional<WarrantyPolicy> findById(Long id) {
        return warrantyPolicyEntityDao.findById(id)
                .map(WarrantyPolicyEntity::toWarrantyPolicy);
    }

    @Override
    public List<WarrantyPolicy> findAll() {
        return warrantyPolicyEntityDao.findAll()
                .stream()
                .map(WarrantyPolicyEntity::toWarrantyPolicy)
                .toList();
    }

    @Override
    public List<WarrantyPolicy> findAllById(List<Long> id) {
        return warrantyPolicyEntityDao.findAllById(id)
                .stream()
                .map(WarrantyPolicyEntity::toWarrantyPolicy)
                .toList();

    }
}
