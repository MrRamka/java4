package com.yabcompany.controllers;

import com.yabcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/twig")
public class TwigController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String index(ModelMap map) {
        map.put("message", "Ramka");
        return "index";
    }

    @RequestMapping("/users")
    public String users(ModelMap map) {
        map.put("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping("/fun")
    public String fun(ModelMap map){
        map.put("post_date", LocalDateTime.now());
        return "function";
    }
}
