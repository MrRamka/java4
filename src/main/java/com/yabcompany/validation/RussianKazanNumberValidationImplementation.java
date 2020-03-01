package com.yabcompany.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RussianKazanNumberValidationImplementation
        implements ConstraintValidator<RussianKazanNumberValidation, String> {
    @Override
    public void initialize(RussianKazanNumberValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        /*
        Regular Expression
        Pass:	+7(916)9985670, 8-912-268-5440, 8905148-3339, 8(913)448-51-90, 903-345-34-34, 903-34-334-34, 903-34-33434
         */
        Pattern russianNumberRegEx =
                Pattern.compile("((8|\\+7)-?)?\\(?\\d{3}\\)?-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}-?\\d{1}");
        Matcher phoneMatcher = russianNumberRegEx.matcher(phoneNumber);
        return phoneMatcher.matches();
    }


}
