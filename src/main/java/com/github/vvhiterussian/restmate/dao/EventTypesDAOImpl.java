package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class EventTypesDAOImpl implements EventTypesDAO {
    private EntityManager entityManager;

    public EventTypesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EventType> getEventTypes() {
        CriteriaQuery<EventType> cq = entityManager.getCriteriaBuilder().createQuery(EventType.class);
        cq.select(cq.from(EventType.class));

        return entityManager.createQuery(cq)
                .getResultList();
    }

    @Override
    public List<EventType> findEventTypes(EventKind eventKind) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EventType> cq = cb.createQuery(EventType.class);

        Root<EventType> eventTypeRoot = cq.from(EventType.class);
        Join<EventType, EventKind> join = eventTypeRoot.join("eventKind", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        if (eventKind != null) {
            predicates.add(cb.equal(eventTypeRoot.get("eventKind"), eventKind));
        }

        cq.select(eventTypeRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq)
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
