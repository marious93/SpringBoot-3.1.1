package com.example.demo.config;

import com.example.demo.entity.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/registration").permitAll() // Publicly accessible
                        .requestMatchers("/user/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers("/admin/**","/users").hasRole(UserRole.ADMIN.name()) // Requires ADMIN role
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specify your custom login page URL
                      //  .usernameParameter("email")
                        .defaultSuccessUrl("/user/{id}")
                        .permitAll() // Allow access to the login page itself
                )
                .logout(LogoutConfigurer::permitAll // Allow everyone to access the logout endpoint
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //шифровальщик паролей
        return new BCryptPasswordEncoder();
    }

}
