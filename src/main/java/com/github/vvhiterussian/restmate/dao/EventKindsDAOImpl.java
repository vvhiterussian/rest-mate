package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class EventKindsDAOImpl implements EventKindsDAO {

    @Autowired
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
        CriteriaQuery<EventKind> cq = entityManager.getCriteriaBuilder().createQuery(EventKind.class);
        cq.select(cq.from(EventKind.class));

        return entityManager.createQuery(cq)
                .getResultList();
    }
}
