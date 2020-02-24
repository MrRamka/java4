package com.yabcompany.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class UserValidator implements Validator {

    @Value("Age is under twelve")
    private String ageUnderTwelve;

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "required.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "required.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "age", "required.age");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "required.password");
        User user = (User) obj;

        if (user.getAge() < 12){
            e.rejectValue("age", ageUnderTwelve);
        }

    }
}
