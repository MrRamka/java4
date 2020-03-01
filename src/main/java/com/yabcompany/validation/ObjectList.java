package com.yabcompany.validation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Constraint(validatedBy = ObjectListValidation.class)
public @interface ObjectList {

    Class<?>[] groups() default {};

    Class<? extends RussianKazanNumberValidation>[] payload() default {};

    String message() default "{Object has empty collections}";
}
