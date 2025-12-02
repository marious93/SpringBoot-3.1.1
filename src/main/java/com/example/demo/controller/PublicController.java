package com.example.demo.controller;

import com.example.demo.entity.MyUser;
import com.example.demo.entity.Role;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("user", new MyUser());
        return "public/create";
    }
//@RequestParam("log") String login,
//                             @RequestParam("password")
    @PostMapping("/")
    public String createUser(@ModelAttribute("user") MyUser user
                              ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("errors");
            return "public/create";
        }
//        MyUser user = new MyUser();
//        user.setLogin(login);
//        user.setPassword(password);
        user.setRole(Role.USER);
        userService.saveUser(user);
        return "redirect:/users";
    }

}
