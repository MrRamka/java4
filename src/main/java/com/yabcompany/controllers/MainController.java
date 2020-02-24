package com.yabcompany.controllers;

import com.yabcompany.models.User;
import com.yabcompany.models.UserValidator;
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

@Controller
@RequestMapping("/registration")
public class MainController {

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());// Task: NPE
    }

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
}
