package com.salescompany.productservice.domain.product.dto.validator;

import com.salescompany.productservice.domain.configs.validator.Validator;
import com.salescompany.productservice.domain.product.dto.CreateUpdateProductDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreateUpdateProductDtoValidator implements Validator<CreateUpdateProductDto> {
    @Override
    public Map<String, String> validate(CreateUpdateProductDto createUpdateProductDto) {

        var errors = new HashMap<String, String>();

        if (createUpdateProductDto == null) {
            errors.put("create update product dto", "is null");
        }

        if (createUpdateProductDto.getName() == null) {
            errors.put("name", "is null");
        } else if (!createUpdateProductDto.getName().matches("[\\w\\s\\-'.]{5,20}+")) {
            errors.put("name", "have wrong format");
        }

        if (createUpdateProductDto.getPrice() == null) {
            errors.put("price", "is null");
        } else if (createUpdateProductDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "is lower than 0");
        }

        if (createUpdateProductDto.getProducerId() == null) {
            errors.put("producer id", "is null");
        } else if (createUpdateProductDto.getProducerId() <= 0L) {
            errors.put("producer id", "is null or negative");
        }

        if (createUpdateProductDto.getCategory() == null) {
            errors.put("category", "is null");
        }

        return errors;

    }
}
