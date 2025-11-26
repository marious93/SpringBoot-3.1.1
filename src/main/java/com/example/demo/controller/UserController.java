package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService revService) {
        this.userService = revService;
    }

    @GetMapping("/{id}")
    public String showUserById(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/info";
    }

    @GetMapping()
    public String getFullList(Model model) {
        model.addAttribute("users", userService.getList());
        return "users/home";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @RequestParam int id) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,
                             @RequestParam int id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
