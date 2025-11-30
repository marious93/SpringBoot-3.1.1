package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name too short")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Mail shouldn't be empty")
    @Size(min = 2, max = 30, message = "Mail too short")
    @Email(message = "Mail should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 2, max = 60, message = "Password too short")
    @Column(name = "password")
    private String password;

    @Min(1)
    @Max(100)
    @Column(name = "age")
    private int age;

    @Column(name="role")
    private UserRole role;

    public User() {
    }


    public User(String name, String email, String password, Integer age, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public @NotEmpty(message = "Password shouldn't be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password shouldn't be empty") String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + email + '\'' +
                ", age=" + age +
                '}';
    }

}
