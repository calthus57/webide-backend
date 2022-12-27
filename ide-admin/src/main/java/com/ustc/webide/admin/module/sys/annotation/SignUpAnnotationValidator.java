package com.ustc.webide.admin.module.sys.annotation;

import com.ustc.webide.admin.module.sys.entity.SysSignupEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SignUpAnnotationValidator implements ConstraintValidator<SignUpAnnotation, SysSignupEntity> {

    @Override
    public void initialize(SignUpAnnotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(SysSignupEntity value, ConstraintValidatorContext context) {
        return false;
    }
}
