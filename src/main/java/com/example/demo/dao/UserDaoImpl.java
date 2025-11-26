package com.example.demo.dao;

import com.example.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private EntityManagerFactory factory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getUserList() {
        List<User> users = null;
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery("from User", User.class);
            users = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery("from User  where id=:id", User.class).
                    setParameter("id", id);
            user = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateUser(Integer id, User updatedUser) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("update User set name =:name, mail=:email, age=:age  where id=:id").
                    setParameter("name", updatedUser.getName()).
                    setParameter("email", updatedUser.getMail()).
                    setParameter("age", updatedUser.getAge()).
                    setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteUser(Integer id) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from User where id=:id").setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}