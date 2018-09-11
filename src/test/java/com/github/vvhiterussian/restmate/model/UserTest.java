package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.EventsDAO;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserTest {

    @Test
    public void TestUserNotOrganizerCannotAddEvent() {
        EventsDAO eventsDAO = mock(EventsDAO.class);
        Event event = mock(Event.class);

        boolean isOrganizer = false;
        User user = new User("test-user-1", "qweqwe", isOrganizer);
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
        User user = new User("test-user-1", "qweqwe", isOrganizer);
        try {
            user.addNewEvent(event);
        } catch (RuntimeException e) {}

        verify(eventsDAO, times(1)).addEvent(event);
        verify(eventsDAO, never()).findEvents(any(EventKind.class), any(EventType.class), anyString());
    }
}
