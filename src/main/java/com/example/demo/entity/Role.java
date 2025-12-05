package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue
    private int id;

    private Roles role;

//    @ManyToOne
//    @JoinTable( name = "user_roles",
//    joinColumns = @JoinColumn(name = "role_id"),
//    inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<MyUser> users;

    public Role() {}
    public Role(Roles role) {
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


//    public Set<MyUser> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<MyUser> users) {
//        this.users = users;
//    }

}
