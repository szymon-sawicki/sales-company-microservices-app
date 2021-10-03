package com.salescompany.productservice.domain.address.dto.validator;

import com.salescompany.productservice.domain.address.dto.CreateUpdateAddressDto;
import com.salescompany.productservice.domain.configs.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class CreateUpdateAddressDtoValidator implements Validator<CreateUpdateAddressDto> {
    @Override
    public Map<String, String> validate(CreateUpdateAddressDto createAddressDto) {

        var errors = new HashMap<String, String>();

        if (createAddressDto == null) {
            errors.put("create address dto", "is null");
            return errors;
        }

        if (createAddressDto.getStreet() == null) {
            errors.put("street", "is null");
        } else if (!createAddressDto.getStreet().matches("[\\-\\w\\s/]{3,40}+")) {
            System.out.println();
            errors.put("street","have wrong format");
        }

        if (createAddressDto.getHouseNumber() == null) {
            errors.put("house number", "is null");
        } else if (!createAddressDto.getHouseNumber().matches("[\\-\\w\\s/]{1,6}+")) {
            errors.put("house number", "have wrong format");
        }

        if (createAddressDto.getCity() == null) {
            errors.put("city", "is null");
        } else {
            if (!createAddressDto.getCity().matches("[\\-\\w\\s]{3,20}+")) {
                errors.put("city", "have wrong format");
            }
        }

        if (createAddressDto.getZipCode() == null) {
            errors.put("zip code", "is null");
        } else {
            if (!createAddressDto.getZipCode().matches("[0-9-]{2,6}+")) {
                errors.put("zip code", "have wrong format");
            }
        }

        return errors;
    }
}
