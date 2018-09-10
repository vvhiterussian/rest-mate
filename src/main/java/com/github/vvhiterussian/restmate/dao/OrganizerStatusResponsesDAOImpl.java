package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrganizerStatusResponsesDAOImpl implements OrganizerStatusResponsesDAO {

    private EntityManager entityManager;

    public OrganizerStatusResponsesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OrganizerStatusResponse> getResponses() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrganizerStatusResponse> cq = cb.createQuery(OrganizerStatusResponse.class);

        cq.select(cq.from(OrganizerStatusResponse.class));

        return entityManager.createQuery(cq)
                .getResultList();
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
