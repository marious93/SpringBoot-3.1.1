package com.example.demo.controller;

import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserServiceImpl userService;

    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "admin/home";
    }
}
