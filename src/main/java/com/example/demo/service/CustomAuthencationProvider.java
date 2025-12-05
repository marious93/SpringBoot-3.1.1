package com.example.demo.service;

import com.example.demo.entity.MyUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {

    private final UserRepository dao;

    public CustomAuthencationProvider(UserRepository dao) {
        this.dao = dao;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        //получаем пользователя
        MyUser myUser = dao.findByUsername(userName);
        //смотрим, найден ли пользователь в базе
        if (myUser == null) {
            throw new BadCredentialsException("Unknown user " + userName);
        }
        if (!password.equals(myUser.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        Set<Role> roles = myUser.getRoles();
        List<Role> stringList = new ArrayList<>(roles);

        UserDetails principal = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(String.valueOf(stringList))
                .build();
        return new UsernamePasswordAuthenticationToken(
                principal, password, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}