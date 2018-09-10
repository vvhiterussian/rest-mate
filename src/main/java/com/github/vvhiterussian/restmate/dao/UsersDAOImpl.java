package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    private EntityManager entityManager;

    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        if (!login.equals("")) {
            predicates.add(cb.equal(user.get("login"), login));
        }

        cq.select(user).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq).getSingleResult();

//        ParameterExpression<String> loginExpression = cb.parameter(String.class);
//        cq.select(user).where(cb.equal(user.get("login"), loginExpression));
//
//        return entityManager.createQuery(cq)
//                .setParameter(loginExpression, login)
//                .getSingleResult();
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
