package com.example.demo.dao;

import com.example.demo.entity.MyUser;

import java.util.List;

public interface UserDao {
    public MyUser findUserById(Integer id);

    public List<MyUser> getUserList();

    public void saveUser(MyUser user);

    public void updateUser(Integer id, MyUser updatedUser);

    public void deleteUser(Integer id);
}