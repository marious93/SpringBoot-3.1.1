package com.example.demo.dao;

import com.example.demo.entity.MyUser;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<MyUser> getUserList() {
        return em.createQuery("from MyUser ", MyUser.class).getResultList();
    }

    @Override
    public MyUser findUserById(Integer id) {
        return em.find(MyUser.class, id);
    }

    @Override
    @Transactional
    public void saveUser(MyUser user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, MyUser updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        em.remove(em.find(MyUser.class, id));
    }

}