package com.pml.cloud.customvalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： libin
 * @email： 909445583@qq.com
 * @date： 2023/3/21
 * @description：
 * @modifiedBy：
 * @version: 1.0
 */
public class EnumNumberValidator implements ConstraintValidator<EnumInteger, Number> {
    private List<Integer> enumStringList;

    @Override
    public void initialize(EnumInteger constraintAnnotation) {
        enumStringList = new ArrayList<>();
        int[] values = constraintAnnotation.value();
        for (int value : values) {
            enumStringList.add(value);
        }
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return true;
        }
        return enumStringList.contains(number.intValue());
    }

}
