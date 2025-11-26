package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    public User getById(Integer id);

    public List<User> getList();

    public void createUser(User user);

    public void updateUser(Integer id, User updatedUser);

    public void deleteUser(Integer id);
}