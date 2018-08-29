package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.EventsDAO;
import com.github.vvhiterussian.restmate.dao.MatesDAO;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserTest {

    static EventsDAO emptyEventsDAO = new EventsDAO() {
        @Override
        public List<Event> findEvents(EventKind eventKind, EventType eventType, String name) {
            return null;
        }

        @Override
        public void addEvent(Event event) {

        }
    };

    static MatesDAO emptyMatesDAO = new MatesDAO() {
        @Override
        public List<User> getMatesByEvent(Event event) {
            return null;
        }

        @Override
        public void addMateToEvent(Event event, User user) {

        }

        @Override
        public void removeMateFromEvent(Event event, User user) {

        }
    };

    @Test
    public void TestUserNotOrganizerCannotAddEventWithEmptyDAOs() {
        AtomicInteger eventCnt = new AtomicInteger(0);

        EventsDAO eventsDAO = new EventsDAO() {
            @Override
            public List<Event> findEvents(EventKind eventKind, EventType eventType, String name) {
                return null;
            }

            @Override
            public void addEvent(Event event) {
                eventCnt.incrementAndGet();
            }
        };

        boolean isOrganizer = false;
        User user = new User("test-user-1", "qweqwe", isOrganizer, eventsDAO);
        Event event = new Event("Test event", "Test event", new EventType("Test event type", new EventKind("Fun")), user, emptyMatesDAO);
        try {
            user.addNewEvent(event);
        } catch (RuntimeException e) {}

        assertEquals(0, eventCnt.get());
    }


    //Tests with Mockito
    @Test
    public void TestUserNotOrganizerCannotAddEvent() {
        EventsDAO eventsDAO = mock(EventsDAO.class);
        Event event = mock(Event.class);

        boolean isOrganizer = false;
        User user = new User("test-user-1", "qweqwe", isOrganizer, eventsDAO);
        try {
            user.addNewEvent(event);
        } catch (RuntimeException e) {}

        verify(eventsDAO, never()).addEvent(event);
        verify(eventsDAO, never()).findEvents(any(EventKind.class), any(EventType.class), anyString());
    }

    @Test
    public void TestUserIsOrganizerCanAddEvent() {
        EventsDAO eventsDAO = mock(EventsDAO.class);
        Event event = mock(Event.class);

        boolean isOrganizer = true;
        User user = new User("test-user-1", "qweqwe", isOrganizer, eventsDAO);
        try {
            user.addNewEvent(event);
        } catch (RuntimeException e) {}

        verify(eventsDAO, times(1)).addEvent(event);
        verify(eventsDAO, never()).findEvents(any(EventKind.class), any(EventType.class), anyString());
    }
}
