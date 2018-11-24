package com.nsa.team10.asgproject.validation;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>
{
    @Override
    public void initialize(PasswordMatches constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context)
    {
        var user = (NewUserDto) value;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
