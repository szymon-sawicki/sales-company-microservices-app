package com.salescompany.productservice.domain.warranty_policy.dto.validator;

import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;
import com.salescompany.productservice.domain.warranty_policy.dto.CreateUpdateWarrantyPolicyDto;

import java.util.HashMap;
import java.util.Map;

public class CreateUpdateWarrantyPolicyDtoValidator implements Validator<CreateUpdateWarrantyPolicyDto> {

    @Override
    public Map<String, String> validate(CreateUpdateWarrantyPolicyDto warrantyPolicyDto) {

        var errors = new HashMap<String,String>();

        if(warrantyPolicyDto == null) {
            errors.put("create update warranty policy dto","is null");
        }

        if(warrantyPolicyDto.getWarrantyPeriod() == null) {
            errors.put("warranty period","is null");
        } else if(warrantyPolicyDto.getWarrantyPeriod() < 0) {
            errors.put("warranty period","is negative");
        }

        if(warrantyPolicyDto.getPossibleServices() == null) {
            errors.put("possible services","are null");
        }

        if(warrantyPolicyDto.getProcessingPeriod() == null) {
            errors.put("processing period","is null");
        } else if (warrantyPolicyDto.getProcessingPeriod() < 0) {
            errors.put("processing period","is negative");
        }

        return errors;
    }
}
