package com.library.management.system.libraryManagementSystem.validators;

import com.library.management.system.libraryManagementSystem.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenderValidatorConstraint implements ConstraintValidator<GenderValidator, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<Gender> genders = List.of(Gender.values());
        return genders.stream().anyMatch(gender -> s.equalsIgnoreCase(gender.name()));
    }
}
