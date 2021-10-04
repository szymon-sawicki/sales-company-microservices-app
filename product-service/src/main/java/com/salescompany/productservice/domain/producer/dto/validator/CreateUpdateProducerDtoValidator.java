package com.salescompany.productservice.domain.producer.dto.validator;

import com.salescompany.productservice.domain.address.dto.validator.CreateUpdateAddressDtoValidator;
import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.producer.dto.CreateUpdateProducerDto;

import java.util.HashMap;
import java.util.Map;

public class CreateUpdateProducerDtoValidator implements Validator<CreateUpdateProducerDto> {

    @Override
    public Map<String, String> validate(CreateUpdateProducerDto createUpdateProducerDto) {
        var errors = new HashMap<String,String>();

        if(createUpdateProducerDto == null) {
            errors.put("create update producer dto","is null");
            return errors;
        }

        if(createUpdateProducerDto.getName() == null) {
            errors.put("name","is null");
        } else if(!createUpdateProducerDto.getName().matches("[\\w\\s\\-'.]{3,20}+")) {
            errors.put("name","have wrong format");
        }

        if(createUpdateProducerDto.getCreateUpdateAddressDto() == null) {
            errors.put("get address dto","is null");
        } else {
            errors.putAll(new CreateUpdateAddressDtoValidator().validate(createUpdateProducerDto.getCreateUpdateAddressDto()));
        }

        var warrantyPolicies = createUpdateProducerDto.getWarrantyPolicies();
        if(warrantyPolicies ==  null) {
            errors.put("warranty policies","are null");
        }

        return errors;

    }
}

