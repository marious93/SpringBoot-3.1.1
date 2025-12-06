package com.example.demo.controller;

import com.example.demo.entity.MyUser;
import com.example.demo.entity.Role;
import com.example.demo.entity.Roles;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.entity.Roles.ADMIN;
import static com.example.demo.entity.Roles.USER;

@Controller
public class PublicController {

    private final UserServiceImpl userService;
    private final RoleService roleService;

    public PublicController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") @Valid MyUser user
                              ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "public/create";
        }
        Role role1 = roleService.getRoleById(1);
        Role role2 = roleService.getRoleById(2);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role2);
//        roles.add(role1);
     //   user.setRoles(roles);
//        roleService.saveRole(role1);
//        roleService.saveRole(role2);
  //      Role role = new Role("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);
        roleService.saveRole(role1);
        roleService.saveRole(role2);
        userService.saveUser(user);
        return "redirect:/users";
    }

}
