package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerStatusRequestsDAOImpl implements OrganizerStatusRequestsDAO {

    @Autowired
    private EntityManager entityManager;

    public OrganizerStatusRequestsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<OrganizerStatusRequest> getRequests() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrganizerStatusRequest> cq = cb.createQuery(OrganizerStatusRequest.class);

        cq.select(cq.from(OrganizerStatusRequest.class));

        return entityManager.createQuery(cq)
                .getResultList();
    }

    @Override
    public List<OrganizerStatusRequest> findRequests(User candidate, boolean hasResponse) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrganizerStatusRequest> cq = cb.createQuery(OrganizerStatusRequest.class);

        Root<OrganizerStatusRequest> requestRoot = cq.from(OrganizerStatusRequest.class);
        Join<OrganizerStatusRequest, User> userJoin = requestRoot.join("candidate", JoinType.INNER);
        Join<OrganizerStatusRequest, OrganizerStatusResponse> responseJoin = requestRoot.join("response", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(hasResponse ? cb.isNotNull(responseJoin.get("id")) : cb.isNull(responseJoin.get("id")));
        if (candidate != null) {
            predicates.add(cb.equal(requestRoot.get("candidate"), candidate));
        }

        cq.select(requestRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq)
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
        if (request.getResponse() != null) {
            throw new RuntimeException("Cannot cancel request that has response.");
        }

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
