package domain.configs;

import domain.configs.validator.ValidatorException;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public interface Validator<T> {
    Map<String,String> validate(T t);

    static <T> void validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        if(!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e-> e.getKey() + ": " + e.getValue())
                    .collect(joining(", "));
            throw new ValidatorException("[VALIDATION ERRORS]: " + message);
        }

    }
}
