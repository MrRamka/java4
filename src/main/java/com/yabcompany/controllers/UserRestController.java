package com.yabcompany.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.yabcompany.api.Views;
import com.yabcompany.entityManager.UserEntityManagerCommands;
import com.yabcompany.models.MainUser;
import com.yabcompany.models.RegistrationForm;
import com.yabcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users-management")
public class UserRestController {

    private final String OK = "OK";
    private final String DELETED = "User deleted";
    private final String EQUAL_EMAIL = "User with this email already exist";
    private final String DOES_NOT_EXIST = "User does not exist";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MainUser> getUsers() {
        UserEntityManagerCommands userEntityManagerCommands = new UserEntityManagerCommands();
        return userEntityManagerCommands.getAllMainUsers();
    }

    @JsonView(Views.Public.class)
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MainUser getUser(@PathVariable("id") Long id) {
        UserEntityManagerCommands userEntityManagerCommands = new UserEntityManagerCommands();
        MainUser mainUser;
        try {
            mainUser = userEntityManagerCommands.getMainUserById(id);
        }catch (NoSuchElementException ex){
            return null;
        }
        return mainUser;
    }

    @JsonView(Views.Public.class)
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody RegistrationForm registrationForm) {
        MainUser mainUser = new MainUser();
        mainUser.setEmail(registrationForm.getEmail());
        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            return ResponseEntity.ok().body(getStatusMessage("Passwords do not equals"));
        }
        mainUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        mainUser.setCountry(registrationForm.getCountry());
        mainUser.setAgreement(registrationForm.getAgreement());
        mainUser.setGender(registrationForm.getGender());
        mainUser.setBirthday(registrationForm.getBirthday());

        try {
            MainUser mainUser1 = userRepository.save(mainUser);
            return ResponseEntity.ok().body(getUserIdMessage(mainUser1.getId()));
        } catch (DataIntegrityViolationException d) {
            return ResponseEntity.ok().body(getStatusMessage(EQUAL_EMAIL));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            MainUser mainUser1 = userRepository.findById(id).get();
            userRepository.delete(mainUser1);
        } catch (NoSuchElementException e) {
            return ResponseEntity.ok().body(getStatusMessage(DOES_NOT_EXIST));
        }
        return ResponseEntity.ok().body(DELETED);
    }

    private String getStatusMessage(String messageText) {
        return "{ \n\t\"message\": \"" + messageText + "\"\n}";
    }

    private String getUserIdMessage(long userId) {
        return "{ \n\t\"user_id\": \"" + userId + "\"}";
    }


}
