package com.salescompany.productservice.application.service;

import com.salescompany.productservice.application.service.exception.AddressesServiceException;
import com.salescompany.productservice.application.service.exception.WarrantyPoliciesServiceException;
import com.salescompany.productservice.domain.warranty_policy.WarrantyPolicy;
import com.salescompany.productservice.domain.warranty_policy.dto.GetWarrantyPolicyDto;
import com.salescompany.productservice.domain.warranty_policy.repository.WarrantyPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarrantyPoliciesService {

    private final WarrantyPolicyRepository warrantyPolicyRepository;

    public GetWarrantyPolicyDto findById(Long id) {
        if(id == null) {
            throw new AddressesServiceException("id is null");
        }
        if(id <= 0) {
            throw new AddressesServiceException("id is 0 or negative");
        }

        return warrantyPolicyRepository.findById(id)
                .map(WarrantyPolicy::toGetWarrantyPolicyDto)
                .orElseThrow(() -> new WarrantyPoliciesServiceException("cannot find by id"));
    }

}
