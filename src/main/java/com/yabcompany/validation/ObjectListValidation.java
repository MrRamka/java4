package com.yabcompany.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Collection;

public class ObjectListValidation implements ConstraintValidator<ObjectList, Object> {
    public void initialize(ObjectList constraint) {
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Field[] allFields = obj.getClass().getDeclaredFields();
        for (Field field : allFields) {
            if (Collection.class.isAssignableFrom(field.getType())) {
                if (Collection.class.cast(field).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
