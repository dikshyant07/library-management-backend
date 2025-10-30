package com.library.management.system.libraryManagementSystem.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidatorConstraint.class)
public @interface GenderValidator {
    String message() default "Please provide MALE,FEMALE or OTHERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
