package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class DAOSmokeTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();
    }

    @After
    public void clean() {
        if (em != null) {
            em.close();
        }

        if (emf != null) {
            emf.close();
        }
    }

    //EventKindsDAO Tests
    @Test
    public void addEventKindAndGetEventKindsTest()
    {
        EventKind eventKind1 = new EventKind("test-event-kind-1");
        EventKind eventKind2 = new EventKind("test-event-kind-2");

        EventKindsDAO eventKindsDAO = new EventKindsDAOImpl(em);
        eventKindsDAO.addEventKind(eventKind1);
        eventKindsDAO.addEventKind(eventKind2);

        assertEquals(2, eventKindsDAO.getEventKinds().size());
        assertEquals(eventKind1, eventKindsDAO.getEventKinds().get(0));
        assertEquals(eventKind2, eventKindsDAO.getEventKinds().get(1));
    }

    //EventTypesDAO Tests
    @Test
    public void addEventTypeAnaGetEventTypes() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        //no need to persist eventKind if @Entity field mapped with cascade setting
        EventKindsDAO eventKindsDAO = new EventKindsDAOImpl(em);
        eventKindsDAO.addEventKind(eventKind);

        EventTypesDAO eventTypesDAO = new EventTypesDAOImpl(em);
        eventTypesDAO.addEventType(eventType);

        assertEquals(1, eventTypesDAO.getEventTypes().size());
        assertEquals(eventType, eventTypesDAO.getEventTypes().get(0));
    }

    @Test
    public void addEventTypeAnaFind() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        //no need to persist eventKind if @Entity field mapped with cascade setting
        EventKindsDAO eventKindsDAO = new EventKindsDAOImpl(em);
        eventKindsDAO.addEventKind(eventKind);

        EventTypesDAO eventTypesDAO = new EventTypesDAOImpl(em);
        eventTypesDAO.addEventType(eventType);

        assertEquals(1, eventTypesDAO.findEventTypes(eventKind).size());
        assertEquals(eventType, eventTypesDAO.findEventTypes(eventKind).get(0));
    }

    //UsersDAO Tests
    @Test
    public void userAddAndFind() {
        UsersDAO usersDAO = new UsersDAOImpl(em);
        EventsDAO eventsDAO = new EventsDAOImpl(em);
        User user = new AdminUser("test-user-1", "password", false, eventsDAO, usersDAO);

        usersDAO.addUser(user);

        assertEquals(user, usersDAO.findByLogin("test-user-1"));
    }

    //EventsDAO Tests
    @Test
    public void addEventAndFind() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        EventKindsDAO eventKindsDAO = new EventKindsDAOImpl(em);
        eventKindsDAO.addEventKind(eventKind);

        EventTypesDAO eventTypesDAO = new EventTypesDAOImpl(em);
        eventTypesDAO.addEventType(eventType);

        EventsDAO eventsDAO = new EventsDAOImpl(em);
        User organizer = new User("test-user-1", "pass", true, eventsDAO);

        UsersDAO usersDAO = new UsersDAOImpl(em);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        eventsDAO.addEvent(event);

        assertEquals(1, eventsDAO.findEvents(eventKind, eventType, "event-1").size());
        assertEquals(event, eventsDAO.findEvents(eventKind, eventType, "event-1").get(0));
    }

    @Test
    public void addMateToEvent() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        EventKindsDAO eventKindsDAO = new EventKindsDAOImpl(em);
        eventKindsDAO.addEventKind(eventKind);

        EventTypesDAO eventTypesDAO = new EventTypesDAOImpl(em);
        eventTypesDAO.addEventType(eventType);

        EventsDAO eventsDAO = new EventsDAOImpl(em);
        User organizer = new User("test-user-1", "pass", true, eventsDAO);

        UsersDAO usersDAO = new UsersDAOImpl(em);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        eventsDAO.addEvent(event);

        User mate = new User("mate-1", "mate-1-pass", false, eventsDAO);
        eventsDAO.addMate(event, mate);

        assertEquals(1, eventsDAO.getMates(event).size());
        assertEquals(mate, eventsDAO.getMates(event).get(0));
    }



    //MateStatusRequestsDAO Tests

    //MateStatusResponsesDAO Tests

    //OrganizerStatusRequestsDAO Tests

    //OrganizerStatusResponsesDAO Tests
}
