package com.food.phat.dao.impl;

import com.food.phat.entity.User;
import com.food.phat.dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    public User getUserById(int userId) {
        Query q = em.createQuery("FROM User WHERE userId = :userId");
        q.setParameter("userId", userId);

        return (User) q.getSingleResult();
    }

    @Override
    public User save(User user) {
        return em.merge(user);
    }

    public User getUserByUsername(String username) {

        Query q = em.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

}

