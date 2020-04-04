package com.yabcompany.controllers;

import com.yabcompany.models.MainUser;
import com.yabcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import com.yabcompany.entityManager.UserEntityManagerCommands;

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
    public String fun(ModelMap map) {
        map.put("post_date", LocalDateTime.now());
        return "function";
    }

    @GetMapping("/user/{userId}")
    public String userEntityManager(ModelMap map, @PathVariable long userId) {
        UserEntityManagerCommands userEntityManagerCommands = new UserEntityManagerCommands();
        MainUser mainUser = userEntityManagerCommands.getMainUserById(userId);
        map.put("user", mainUser);
        return "user";
    }
}
