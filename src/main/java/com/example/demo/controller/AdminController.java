package com.example.demo.controller;

import com.example.demo.entity.MyUser;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
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

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user")@Valid MyUser user, BindingResult bindingResult,
                             @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/info";
    }

}
