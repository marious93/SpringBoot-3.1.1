package com.example.demo.controller;

import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "private/info";
    }




}
