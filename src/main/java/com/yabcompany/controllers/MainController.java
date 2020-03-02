package com.yabcompany.controllers;

import com.yabcompany.models.MainUser;
import com.yabcompany.models.RegistrationForm;
import com.yabcompany.models.User;
import com.yabcompany.models.UserValidator;
import com.yabcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequestMapping("/registration")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        try {
//            binder.setValidator(new UserValidator());
//        } catch (NullPointerException e) {
//
//        }
//    }

    @RequestMapping
    public String loginPage() {
        return "index";
    }

    @RequestMapping(value = "new_user", method = RequestMethod.GET)
    public String newUser(ModelMap map) {
        map.put("user", new User());
        return "registration";
    }


    @RequestMapping(value = "new_user", method = RequestMethod.POST)
    public String newUser(
            ModelMap map,
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            System.out.println(user.toString());
            redirectAttributes.addFlashAttribute("message", "User " + user.getName() + " has been added successfully");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("JC#newUser").build();
        }

    }

    @RequestMapping(value = "/users")
    public String list(ModelMap map) {
        map.put("users", userRepository.findAll());
        return "users/list";
    }

    @RequestMapping(value = "/user_reg", method = RequestMethod.GET)
    public String regUser(ModelMap map) {
        map.put("registrationForm", new RegistrationForm());
        return "users/user_reg_form";
    }

    @RequestMapping(value = "/user_reg", method = RequestMethod.POST)
    public String regUserHandler(ModelMap map,
                                 RedirectAttributes redirectAttributes,
                                 @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/user_reg_form";
        } else {
            MainUser mainUser = new MainUser();
            mainUser.setEmail(registrationForm.getEmail());
            mainUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
            mainUser.setCountry(registrationForm.getCountry());
            mainUser.setAgreement(registrationForm.getAgreement());
            mainUser.setGender(registrationForm.getGender());
            mainUser.setBirthday(registrationForm.getBirthday());

            try {
                userRepository.save(mainUser);
            } catch (DataIntegrityViolationException d) {
                redirectAttributes.addFlashAttribute("message", "User " + registrationForm.getEmail()
                        + " already exist");
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#regUser").build();
            }

            redirectAttributes.addFlashAttribute("message", "User " + registrationForm.getEmail()
                    + " has been added successfully");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#regUser").build();
        }
    }


}
