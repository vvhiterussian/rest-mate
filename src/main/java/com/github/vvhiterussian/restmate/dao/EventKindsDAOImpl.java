package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;

import javax.persistence.EntityManager;
import java.util.List;

public class EventKindsDAOImpl implements EventKindsDAO {

    private EntityManager entityManager;

    public EventKindsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EventKind> getEventKinds() {
        return entityManager.createQuery("select ek from EventKind ek")
                .getResultList();
    }
}
