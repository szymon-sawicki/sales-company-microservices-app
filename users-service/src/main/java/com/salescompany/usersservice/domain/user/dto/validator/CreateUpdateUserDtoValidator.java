package com.salescompany.usersservice.domain.user.dto.validator;

import com.salescompany.usersservice.domain.configs.validator.Validator;
import com.salescompany.usersservice.domain.user.dto.CreateUpdateUserDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class CreateUpdateUserDtoValidator implements Validator<CreateUpdateUserDto> {

    @Override
    public Map<String, String> validate(CreateUpdateUserDto createUpdateUserDto) {
        var errors = new HashMap<String,String>();

        if(createUpdateUserDto == null) {
            errors.put("create update user dto", "is null");
            return errors;
        }

        if (createUpdateUserDto.getFirstName() == null) {
            errors.put("first name", "is null");
        } else if (!createUpdateUserDto.getFirstName().matches("[\\w\\s\\-'.]{3,20}+")) {
            errors.put("first name", "have wrong format");
        }

        if (createUpdateUserDto.getLastName() == null) {
            errors.put("last name", "is null");
        } else if (!createUpdateUserDto.getLastName().matches("[\\w\\s\\-'.]{3,20}+")) {
            errors.put("last name", "have wrong format");
        }

        if (createUpdateUserDto.getUsername() == null) {
            errors.put("username", "is null");
        } else if (!createUpdateUserDto.getUsername().matches("[\\w\\s\\-'.]{5,20}+")) {
            errors.put("username", "have wrong format");
        }

        if (createUpdateUserDto.getPassword() == null) {
            errors.put("password", "is null");
        } else if (!createUpdateUserDto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*]).{8,}$")) {
            errors.put("password", "have wrong format");
        }

        if (createUpdateUserDto.getMail() == null) {
            errors.put("mail", "is null");
        } else if (!createUpdateUserDto.getMail().matches("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            errors.put("mail", "have wrong format");
        }

        if (createUpdateUserDto.getBirthDate() == null) {
            errors.put("birth date", "is null");
        } else {
            if (createUpdateUserDto.getBirthDate().isAfter(LocalDate.now().minusYears(10))) {
                errors.put("birth date", "is out of range");
            }
        }

        if(createUpdateUserDto.getGender() == null) {
            errors.put("gender","is null");
        }

        return errors;

    }
}
