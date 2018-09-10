package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.MateStatusRequest;
import com.github.vvhiterussian.restmate.model.MateStatusResponse;
import com.github.vvhiterussian.restmate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class MateStatusRequestsDAOImpl implements MateStatusRequestsDAO {

    private EntityManager entityManager;

    public MateStatusRequestsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MateStatusRequest> getRequests() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MateStatusRequest> cq = cb.createQuery(MateStatusRequest.class);

        cq.select(cq.from(MateStatusRequest.class));

        return entityManager.createQuery(cq)
                .getResultList();
    }

    @Override
    public List<MateStatusRequest> findRequests(Event event, User user, boolean hasResponse) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MateStatusRequest> cq = cb.createQuery(MateStatusRequest.class);

        Root<MateStatusRequest> requestRoot = cq.from(MateStatusRequest.class);
        Join<MateStatusRequest, Event> eventJoin = requestRoot.join("event", JoinType.INNER);
        Join<MateStatusRequest, User> userJoin = requestRoot.join("candidate", JoinType.INNER);
        Join<MateStatusRequest, MateStatusResponse> responseJoin = requestRoot.join("response", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(hasResponse ? cb.isNotNull(responseJoin.get("id")) : cb.isNull(responseJoin.get("id")));
        if (event != null) {
            predicates.add(cb.equal(requestRoot.get("event"), event));
        }

        if (user != null) {
            predicates.add(cb.equal(requestRoot.get("candidate"), user));
        }

        cq.select(requestRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(cq)
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
