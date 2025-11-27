package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Integer id) {
        return userDao.findUserById(id);
    }

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public void saveUser(User rev) {
        userDao.saveUser(rev);
    }

    public void updateUser(Integer id, User rev) {
        userDao.updateUser(id, rev);
    }

    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

}
