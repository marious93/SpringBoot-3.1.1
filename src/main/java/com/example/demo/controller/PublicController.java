package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class PublicController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PublicController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "public/loginPage";
    }

    @GetMapping("/")
    public String home() {
        return "public/homePage";
    }

    @GetMapping("/registration")
    public String createUserPage(Model model) {
        model.addAttribute("user", new User());
        return "public/create";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "public/create";
        }
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);
        user.setAge(1);
        userService.saveUser(user);
        return "redirect:/users";
    }

}
