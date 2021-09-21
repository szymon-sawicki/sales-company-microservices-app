package domain.user.dto.validator;

import domain.configs.Validator;
import domain.user.dto.CreateUpdateUserDto;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class CreateUpdateUserDtoValidator implements Validator<CreateUpdateUserDto> {

    @Override
    public Map<String, String> validate(CreateUpdateUserDto createUpdateUserDto) {
        var errors = new HashMap<String,String>();

        TODO


        return errors;

    }
}
