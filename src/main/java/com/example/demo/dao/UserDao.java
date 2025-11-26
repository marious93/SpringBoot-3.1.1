package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {
    public User findById(Integer id);

    public List<User> getUserList();

    public void saveUser(User user);

    public void updateUser(Integer id, User updatedUser);

    public void deleteUser(Integer id);
}