package com.example.demo.dao;

import com.example.demo.entity.User;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<User> getUserList() {
        List<User> list = null;
        try {
            list = em.createQuery("from User ", User.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public User findUserById(Integer id) {
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User updatedUser) {
        try {
            em.merge(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        try {
            em.remove(em.find(User.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}