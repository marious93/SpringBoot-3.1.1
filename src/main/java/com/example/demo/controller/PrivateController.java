package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
public class PrivateController {

    private final UserServiceImpl userService;

    public PrivateController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("user", userService.getUserById(id));
        return "private/info";
    }

    @GetMapping()
    public String showUsersList(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "private/home";
    }



    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) throws Exception {
        model.addAttribute("user", userService.getUserById(id));
        return "private/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "private/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
