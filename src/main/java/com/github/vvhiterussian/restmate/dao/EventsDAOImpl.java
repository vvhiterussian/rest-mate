package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;
import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventsDAOImpl implements EventsDAO {
    private EntityManager entityManager;

    public EventsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Event> findEvents(EventKind eventKind, EventType eventType, String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);

        Root<Event> eventRoot = cq.from(Event.class);
        Join<Event, EventType> eventTypeJoin = eventRoot.join("eventType", JoinType.INNER);
        Join<EventType, EventKind> eventKindJoin = eventTypeJoin.join("eventKind", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        if (eventKind != null) {
            predicates.add(cb.equal(eventTypeJoin.get("eventKind"), eventKind));
        }

        if (eventType != null) {
            predicates.add(cb.equal(eventRoot.get("eventType"), eventType));
        }

        if (!name.equals("")) {
            predicates.add(cb.like(eventRoot.get("name"), "%" + name + "%"));
        }

        cq.select(eventRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq)
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

    @Override
    public Set<User> getMates(Event event) {
        return event.getMates();
    }
}
