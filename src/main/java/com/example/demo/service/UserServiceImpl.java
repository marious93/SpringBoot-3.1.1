package com.example.demo.service;


import com.example.demo.entity.MyUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public MyUser getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<MyUser> getUserList() {
        return userRepository.findAll();
    }

    public void saveUser(MyUser user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, MyUser user) {
        MyUser usertoUpdate = userRepository.findById(id).orElseThrow();
        System.out.println(usertoUpdate);
        usertoUpdate.setPassword(user.getPassword());
        usertoUpdate.setLogin(user.getLogin());
        usertoUpdate.setAge(user.getAge());
        usertoUpdate.setMail(user.getMail());
        userRepository.save(usertoUpdate);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found with username ", username));
        }
        return new User(user.getLogin(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

}
