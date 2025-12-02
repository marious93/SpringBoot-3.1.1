package com.example.demo.service;


import com.example.demo.entity.MyUser;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public MyUser getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<MyUser> getUserList() {
        return userRepository.findAll();
    }

    public void saveUser(MyUser user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, MyUser user) {
        MyUser usertoUpdate = userRepository.findById(id).orElseThrow();
        System.out.println(usertoUpdate);
        usertoUpdate.setPassword(user.getPassword());
        usertoUpdate.setLogin(user.getLogin());
        usertoUpdate.setAge(user.getAge());
        usertoUpdate.setMail(user.getMail());
        userRepository.save(usertoUpdate);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
