package com.salescompany.orderservice.domain.order.dto.validator;

import com.salescompany.orderservice.domain.configs.validator.Validator;
import com.salescompany.orderservice.domain.order.dto.CreateUpdateOrderDto;

import java.util.HashMap;
import java.util.Map;

public class CreateUpdateOrderDtoValidator implements Validator<CreateUpdateOrderDto> {

    @Override
    public Map<String, String> validate(CreateUpdateOrderDto createUpdateValidatorDto) {

        var errors = new HashMap<String,String>();

        if(createUpdateValidatorDto == null) {
            errors.put("create update order dto","is null");
            return errors;
        }

        if(createUpdateValidatorDto.getCustomerId() == null) {
            errors.put("customer id","is null");
        } else if(createUpdateValidatorDto.getCustomerId() <= 0) {
            errors.put("customer id","is 0 or negative");
        }

        if(createUpdateValidatorDto.getManagerId() == null) {
            errors.put("manager id","is null");
        } else if(createUpdateValidatorDto.getManagerId() <= 0) {
            errors.put("manager id","is 0 or negative");
        }

        if(createUpdateValidatorDto.getShopId() == null) {
            errors.put("shop id","is null");
        } else if(createUpdateValidatorDto.getShopId() <= 0) {
            errors.put("shop id","is 0 or negative");
        }

        if(createUpdateValidatorDto.getProductsMap() == null) {
            errors.put("products map","is null");
        } else if(createUpdateValidatorDto.getProductsMap().isEmpty()) {
            errors.put("products map","is empty");
        }

        return errors;
    }
}
