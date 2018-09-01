package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class EventTypesDAOImpl implements EventTypesDAO {
    private EntityManager entityManager;

    public EventTypesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EventType> getEventTypes() {
        return entityManager.createQuery("select et from EventType et")
                .getResultList();
    }

    @Override
    public List<EventType> findEventTypes(EventKind eventKind) {
        return entityManager.createQuery("select et from EventType et where et.eventKind = :eventKind")
                .setParameter("eventKind", eventKind)
                .getResultList();
    }

    @Override
    public void addEventType(EventType eventType) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(eventType);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
