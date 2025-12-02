package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        User usertoUpdate = userRepository.findById(id).orElseThrow();
        usertoUpdate.setName(user.getName());
        usertoUpdate.setAge(user.getAge());
        usertoUpdate.setMail(user.getMail());
        userRepository.save(usertoUpdate);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
