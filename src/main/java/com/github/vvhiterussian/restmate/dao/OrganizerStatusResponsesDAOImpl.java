package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.OrganizerStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class OrganizerStatusResponsesDAOImpl implements OrganizerStatusResponsesDAO {

    @Autowired
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
