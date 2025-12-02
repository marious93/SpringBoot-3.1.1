package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name too short")
    @Column(name = "login")
    private String login;


    @Email(message = "Mail should be valid")
    @Column(name = "mail")
    private String mail;

    private String password;

    private Role role;


    @Max(100)
    @Column(name = "age")
    private int age;

    public MyUser() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String firstName) {
        this.login = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                '}';
    }

}
