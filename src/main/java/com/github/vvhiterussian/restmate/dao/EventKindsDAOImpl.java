package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class EventKindsDAOImpl implements EventKindsDAO {

    private EntityManager entityManager;

    public EventKindsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addEventKind(EventKind eventKind) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(eventKind);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<EventKind> getEventKinds() {
        return entityManager.createQuery("select ek from EventKind ek")
                .getResultList();
    }
}
