package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;
import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class EventsDAOImpl implements EventsDAO {
    private EntityManager entityManager;

    public EventsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Event> findEvents(EventKind eventKind, EventType eventType, String name) {
        return entityManager.createQuery("select e from Event e where e.eventType = :eventType or e.eventType.eventKind = :eventKind or e.name like :name")
                .setParameter("eventType", eventType)
                .setParameter("eventKind", eventKind)
                .setParameter("name", "%" + eventType + "%")
                .getResultList();
    }

    @Override
    public void addEvent(Event event) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(event);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void addMate(Event event, User user) {
        entityManager.getTransaction().begin();

        try {
            event.getMates().add(user);

            entityManager.persist(event);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void removeMate(Event event, User user) {
        entityManager.getTransaction().begin();

        try {
            event.getMates().remove(user);

            entityManager.persist(event);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
