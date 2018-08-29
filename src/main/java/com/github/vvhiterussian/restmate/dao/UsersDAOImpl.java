package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class UsersDAOImpl implements UsersDAO {
    private EntityManager entityManager;

    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByLogin(String login) {
        return (User)entityManager.createQuery("select u from User u where u.login like :login")
                .setParameter("login", "%" + login + "%")
                .getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
