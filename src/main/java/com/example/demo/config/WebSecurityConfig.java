package com.example.demo.config;

import com.example.demo.service.CustomAuthencationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index","/new","/home").permitAll()
                //.antMatchers("/users").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u1")
//                        .password("p1")
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("u2")
//                        .password("p2")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Autowired
    private CustomAuthencationProvider customAuthencationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //и добавляем его сюда
        auth.authenticationProvider(customAuthencationProvider);
    }

}