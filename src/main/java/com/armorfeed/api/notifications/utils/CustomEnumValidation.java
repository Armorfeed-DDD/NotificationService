package com.armorfeed.api.notifications.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEnumValidation implements ConstraintValidator<ValidateCustomEnum, CharSequence> {
    private List<String> validValues;

    @Override
    public void initialize(ValidateCustomEnum constraint) {
        validValues = Stream.of(constraint.enumClass().getEnumConstants())
            .map(Enum::toString)
            .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if(value == null) return false;
        return validValues.contains(value);
    }
    
}
