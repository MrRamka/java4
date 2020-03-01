package com.yabcompany.validation;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = RussianKazanNumberValidationImplementation.class)
public @interface RussianKazanNumberValidation {

    Class<?>[] groups() default {};

    Class<? extends RussianKazanNumberValidation>[] payload() default {};

    String message() default "{Incorrect phone number for Kazan/Russia}";
}
