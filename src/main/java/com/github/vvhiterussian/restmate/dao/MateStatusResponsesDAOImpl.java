package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.MateStatusResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MateStatusResponsesDAOImpl implements MateStatusResponsesDAO {
    private EntityManager entityManager;

    public MateStatusResponsesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MateStatusResponse> getResponses() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MateStatusResponse> cq = cb.createQuery(MateStatusResponse.class);

        cq.select(cq.from(MateStatusResponse.class));

        return entityManager.createQuery(cq)
                .getResultList();
    }

    @Override
    public void addResponse(MateStatusResponse response) {
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
