package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.MateStatusResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class MateStatusResponsesDAOImpl implements MateStatusResponsesDAO {
    private EntityManager entityManager;

    public MateStatusResponsesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addResponse(MateStatusResponse response) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(response);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
