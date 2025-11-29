package com.example.demo.service;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  {

    private final UserRepository userDao;

    @Autowired
    public UserServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Integer id) throws Exception {
        return userDao.findById(id).orElseThrow(()->new Exception("no such user"));
    }

    public List<User> getUserList() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public void updateUser(Integer id, User user) {
        userDao.findById(id).ifPresent(u->{
            u.setAge(user.getAge());
            u.setMail(user.getMail());
            u.setName(user.getName());
        });
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

}
