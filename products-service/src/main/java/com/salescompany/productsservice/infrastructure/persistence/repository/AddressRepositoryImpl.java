package com.salescompany.productsservice.infrastructure.persistence.repository;

import com.salescompany.productsservice.domain.address.Address;
import com.salescompany.productsservice.domain.address.repository.AddressRepository;
import com.salescompany.productsservice.infrastructure.persistence.dao.AddressEntityDao;
import com.salescompany.productsservice.infrastructure.persistence.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressEntityDao addressEntityDao;

    @Override
    public Optional<Address> add(Address address) {
        var insertedAddress = addressEntityDao.save(address.toEntity());
        return Optional.of(insertedAddress.toAddress());
    }

    @Override
    public Optional<Address> delete(Long id) {
        var addressToDelete = addressEntityDao
                .findById(id)
                .orElseThrow(() -> new PersistenceException("cannot find address to delete"));
        addressEntityDao.delete(addressToDelete);
        return Optional.of(addressToDelete.toAddress());
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressEntityDao.findById(id)
                .map(AddressEntity::toAddress);
    }

    @Override
    public List<Address> findAll() {
        return addressEntityDao.findAll()
                .stream()
                .map(AddressEntity::toAddress)
                .toList();
    }

    @Override
    public List<Address> findAllById(List<Long> longs) {
        return null;
    }
}
