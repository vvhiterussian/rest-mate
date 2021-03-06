package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.*;
import com.github.vvhiterussian.restmate.web.config.TestEntityManagerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = TestEntityManagerConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DAOSmokeTest {

    @Autowired
    private EntityManager em;

    @Autowired
    EventKindsDAO eventKindsDAO;

    @Autowired
    EventTypesDAO eventTypesDAO;

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    EventsDAO eventsDAO;

    @Autowired
    MateStatusRequestsDAO mateStatusRequestsDAO;

    @Autowired
    MateStatusResponsesDAO mateStatusResponsesDAO;

    @Autowired
    OrganizerStatusRequestsDAO organizerStatusRequestsDAO;

    @Autowired
    OrganizerStatusResponsesDAO organizerStatusResponsesDAO;

    //EventKindsDAO Tests
    @Test
    public void addEventKindAndGetEventKindsTest()
    {
        EventKind eventKind1 = new EventKind("test-event-kind-1");
        EventKind eventKind2 = new EventKind("test-event-kind-2");

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
        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        assertEquals(1, eventTypesDAO.getEventTypes().size());
        assertEquals(eventType, eventTypesDAO.getEventTypes().get(0));
    }

    @Test
    public void addEventTypeAnaFind() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        //no need to persist eventKind if @Entity field mapped with cascade setting
        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        assertEquals(1, eventTypesDAO.findEventTypes(eventKind).size());
        assertEquals(eventType, eventTypesDAO.findEventTypes(eventKind).get(0));
    }

    //UsersDAO Tests
    @Test
    public void userAddAndFind() {
        User user = new AdminUser("test-user-1", "password", false);
        usersDAO.addUser(user);
        assertEquals(user, usersDAO.findByLogin("test-user-1"));
    }

    //EventsDAO Tests
    @Test
    public void addEventAndFind() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        eventsDAO.addEvent(event);

        assertEquals(1, eventsDAO.findEvents(eventKind, eventType, "event-1").size());
        assertEquals(1, eventsDAO.findEvents(eventKind, eventType, "").size());
        assertEquals(1, eventsDAO.findEvents(eventKind, null, "event-1").size());
        assertEquals(1, eventsDAO.findEvents(null, eventType, "event-1").size());
        assertEquals(0, eventsDAO.findEvents(null, null, "event-2").size());
        assertEquals(event, eventsDAO.findEvents(eventKind, eventType, "event-1").get(0));
    }

    @Test
    public void addMateToEvent() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        eventsDAO.addEvent(event);

        User mate = new User("mate-1", "mate-1-pass", false);
        eventsDAO.addMate(event, mate);

        assertEquals(1, eventsDAO.getMates(event).size());
        assertEquals(mate, eventsDAO.getMates(event).toArray()[0]);
    }

    @Test
    public void removeMateFromEvent() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        eventsDAO.addEvent(event);

        User mate = new User("mate-1", "mate-1-pass", false);
        eventsDAO.addMate(event, mate);

        assertEquals(1, eventsDAO.getMates(event).size());

        User userToRemove = usersDAO.findByLogin("mate-1");
        eventsDAO.removeMate(event, userToRemove);

        assertEquals(0, eventsDAO.getMates(event).size());
    }


    //MateStatusRequestsDAO Tests
    @Test
    public void addMateStatusRequestAndGetRequests() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        User candidate = new User("candidate-1", "mate-1-pass", false);
        eventsDAO.addEvent(event);
        usersDAO.addUser(candidate);

        MateStatusRequest request = new MateStatusRequest(event, candidate);
        mateStatusRequestsDAO.addRequest(request);

        assertEquals(1, mateStatusRequestsDAO.getRequests().size());
        assertEquals(request, mateStatusRequestsDAO.getRequests().get(0));
    }

    @Test
    public void addMateStatusRequestAndFind() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        User candidate = new User("candidate-1", "mate-1-pass", false);
        eventsDAO.addEvent(event);
        usersDAO.addUser(candidate);

        MateStatusRequest request = new MateStatusRequest(event, candidate);
        mateStatusRequestsDAO.addRequest(request);

        assertEquals(0, mateStatusRequestsDAO.findRequests(event, candidate, true).size());
        assertEquals(1, mateStatusRequestsDAO.findRequests(event, candidate, false).size());
        assertEquals(1, mateStatusRequestsDAO.findRequests(event, null, false).size());
        assertEquals(1, mateStatusRequestsDAO.findRequests(null, candidate, false).size());
        assertEquals(1, mateStatusRequestsDAO.findRequests(null, null, false).size());

    }

    @Test
    public void cancelMateStatusRequest() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        User candidate = new User("candidate-1", "mate-1-pass", false);
        eventsDAO.addEvent(event);
        usersDAO.addUser(candidate);

        MateStatusRequest request = new MateStatusRequest(event, candidate);
        mateStatusRequestsDAO.addRequest(request);

        assertEquals(1, mateStatusRequestsDAO.findRequests(event, candidate, false).size());

        mateStatusRequestsDAO.cancelRequest(request);
        assertEquals(0, mateStatusRequestsDAO.findRequests(null, null, false).size());
        assertEquals(0, mateStatusRequestsDAO.findRequests(null, null, true).size());
    }

    //MateStatusResponsesDAO Tests
    @Test
    public void addMateStatusResponseAndGetResponses() {
        EventKind eventKind = new EventKind("test-event-kind-1");
        EventType eventType = new EventType("test-event-type-1", eventKind);

        eventKindsDAO.addEventKind(eventKind);
        eventTypesDAO.addEventType(eventType);

        User organizer = new User("test-user-1", "pass", true);
        usersDAO.addUser(organizer);

        Event event = new Event("test-event-1", "test-event-1", eventType, organizer);
        User candidate = new User("candidate-1", "mate-1-pass", false);
        eventsDAO.addEvent(event);
        usersDAO.addUser(candidate);

        MateStatusRequest request = new MateStatusRequest(event, candidate);
        mateStatusRequestsDAO.addRequest(request);

        MateStatusResponse response = new MateStatusResponse(true, organizer, request);
        mateStatusResponsesDAO.addResponse(response);

        assertEquals(1, mateStatusResponsesDAO.getResponses().size());
    }


    //OrganizerStatusRequestsDAO Tests
    @Test
    public void addOrganizerStatusRequestAndGetRequests() {
        User candidate = new User("test-user-1", "pass", true);
        usersDAO.addUser(candidate);

        OrganizerStatusRequest request = new OrganizerStatusRequest(candidate);
        organizerStatusRequestsDAO.addRequest(request);

        assertEquals(1, organizerStatusRequestsDAO.getRequests().size());
        assertEquals(request, organizerStatusRequestsDAO.getRequests().get(0));
    }

    @Test
    public void addOrganizerStatusRequestAndFind() {
        User candidate = new User("test-user-1", "pass", true);
        usersDAO.addUser(candidate);

        OrganizerStatusRequest request = new OrganizerStatusRequest(candidate);
        organizerStatusRequestsDAO.addRequest(request);

        assertEquals(0, organizerStatusRequestsDAO.findRequests(candidate, true).size());
        assertEquals(1, organizerStatusRequestsDAO.findRequests(candidate, false).size());
        assertEquals(1, organizerStatusRequestsDAO.findRequests(null, false).size());
    }

    @Test
    public void cancelOrganizerStatusRequestAndGetRequests() {
        User candidate = new User("test-user-1", "pass", true);
        usersDAO.addUser(candidate);

        OrganizerStatusRequest request = new OrganizerStatusRequest(candidate);
        organizerStatusRequestsDAO.addRequest(request);

        assertEquals(1, organizerStatusRequestsDAO.getRequests().size());

        organizerStatusRequestsDAO.cancelRequest(request);
        assertEquals(0, organizerStatusRequestsDAO.getRequests().size());
    }

    //OrganizerStatusResponsesDAO Tests
    @Test
    public void addOrganizerStatusResponseAndGetResponses() {
        User candidate = new User("test-user-1", "pass", true);
        User observer = new AdminUser("observer", "observer", true);
        usersDAO.addUser(candidate);
        usersDAO.addUser(observer);

        OrganizerStatusRequest request = new OrganizerStatusRequest(candidate);
        organizerStatusRequestsDAO.addRequest(request);

        assertEquals(1, organizerStatusRequestsDAO.getRequests().size());

        OrganizerStatusResponse response = new OrganizerStatusResponse(true, observer, request);
        organizerStatusResponsesDAO.addResponse(response);

        assertEquals(1, organizerStatusResponsesDAO.getResponses().size());
    }
}
