package com.example.demo.controller;

import com.example.demo.entity.MyUser;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "private/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user")@Valid MyUser user, BindingResult bindingResult,
                             @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "private/edit";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }

}
