package ru.ugrinovich.Spectra.validation.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.ugrinovich.Spectra.validation.annotations.EnumValidate;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValidate, Enum<?>> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        return Arrays.asList(enumClass.getEnumConstants()).contains(value);
    }
}
