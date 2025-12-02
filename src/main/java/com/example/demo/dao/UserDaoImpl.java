package com.example.demo.dao;

import com.example.demo.entity.User;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<User> getUserList() {
        return em.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public User findUserById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        em.remove(em.find(User.class, id));
    }

}