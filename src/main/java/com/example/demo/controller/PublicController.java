package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PublicController {

    private final UserServiceImpl userService;

    public PublicController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String index(Model model) {
        return "public/HomePage";
    }

    @GetMapping("/new")
    public String createUserPage(Model model) {
        model.addAttribute("user", new User());
        return "public/create";
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "public/create";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

}
