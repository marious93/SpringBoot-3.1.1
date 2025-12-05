package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

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

    @Max(100)
    @Column(name = "age")
    private int age;

//    private String role;
//
//    public String getRole() {
//        return role;
//    }

//    public void setRole(Roles role) {
//        this.role = String.valueOf(role);
//    }

    @ManyToMany
    @JoinTable (name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public MyUser() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
