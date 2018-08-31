package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class OrganizerStatusResponsesDAOImpl implements OrganizerStatusResponsesDAO {

    private EntityManager entityManager;

    public OrganizerStatusResponsesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addResponse(OrganizerStatusResponse response) {
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
