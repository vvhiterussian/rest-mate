package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.MateStatusRequest;
import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class MateStatusRequestsDAOImpl implements MateStatusRequestsDAO {

    private EntityManager entityManager;

    public MateStatusRequestsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MateStatusRequest> getRequests() {
        return entityManager.createQuery("select msr from MateStatusRequest msr")
                .getResultList();
    }

    @Override
    public List<MateStatusRequest> findRequests(Event event, User user, boolean hasResponse) {
        String findRequestsQuery =
                "select msr " +
                "from MateStatusRequest msr " +
                "where msr.event = :event or msr.candidate = :user or " +
                "((:hasResponse = true and msr.response is not null) or (:hasResponse = false and msr.response is null))";

        return entityManager.createQuery(findRequestsQuery)
                .setParameter("event", event)
                .setParameter("user", user)
                .setParameter("hasResponse", hasResponse)
                .getResultList();
    }

    @Override
    public void addRequest(MateStatusRequest request) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(request);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void cancelRequest(MateStatusRequest request) {
        entityManager.getTransaction().begin();

        try {
            entityManager.remove(request);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
