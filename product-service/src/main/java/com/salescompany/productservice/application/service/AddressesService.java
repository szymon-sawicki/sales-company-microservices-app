package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.AddressesServiceException;
import com.salescompany.productservice.domain.address.Address;
import com.salescompany.productservice.domain.address.dto.GetAddressDto;
import com.salescompany.productservice.domain.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressesService {

    private final AddressRepository addressRepository;

    public GetAddressDto findById(Long id) {
        if(id == null) {
            throw new AddressesServiceException("id is null");
        }
        if(id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }

        return addressRepository.findById(id)
                .map(Address::toGetAddressDto)
                .orElseThrow(() -> new AddressesServiceException("cannot find address"));
    }

}
