package com.yabcompany.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.yabcompany.api.Views;
import com.yabcompany.models.MainUser;
import com.yabcompany.models.RegistrationForm;
import com.yabcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users-management")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MainUser> getUsers() {
        return (List<MainUser>) userRepository.findAll();
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MainUser getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @JsonView(Views.Public.class)
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody RegistrationForm registrationForm) {
        MainUser mainUser = new MainUser();
        mainUser.setEmail(registrationForm.getEmail());
        mainUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        mainUser.setCountry(registrationForm.getCountry());
        mainUser.setAgreement(registrationForm.getAgreement());
        mainUser.setGender(registrationForm.getGender());
        mainUser.setBirthday(registrationForm.getBirthday());

        try {
           MainUser mainUser1 =  userRepository.save(mainUser);
            return "{ \"user_id\": \"" + mainUser1.getId() + "\"";
        } catch (DataIntegrityViolationException d) {
            return "{ \"message\": \"User with this email already exist\"}";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        String message = "";
        try {
            MainUser mainUser1 =  userRepository.findById(id).get();
            userRepository.delete(mainUser1);
        }catch (NoSuchElementException e){
            return "{ \"message\": \"User does not exist\"}";
        }
        return "{ \"message\": \"OK\"}";
    }

}
