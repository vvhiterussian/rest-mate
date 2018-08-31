package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusRequest;
import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class OrganizerStatusRequestsDAOImpl implements OrganizerStatusRequestsDAO {
    private EntityManager entityManager;

    public OrganizerStatusRequestsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OrganizerStatusRequest> getRequests() {
        return entityManager.createQuery("select osr from OrganizerStatusRequest osr")
                .getResultList();
    }

    @Override
    public List<OrganizerStatusRequest> findRequests(User user, boolean hasResponse) {
        String findRequestsQuery =
                "select osr " +
                "from OrganizerStatusRequest osr " +
                "where osr.candidate = :user or " +
                "((:hasResponse = true and osr.response is not null) or (:hasResponse = false and osr.response is null))";

        return entityManager.createQuery(findRequestsQuery)
                .setParameter("user", user)
                .setParameter("hasResponse", hasResponse)
                .getResultList();
    }

    @Override
    public void addRequest(OrganizerStatusRequest request) {
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
    public void cancelRequest(OrganizerStatusRequest request) {
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
