package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.MatesDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class EventTest {

    @Test
    public void TestAddMateToEvent() {
        MatesDAO matesDAO = mock(MatesDAO.class);
        User organizer = mock(User.class);
        Event event = new Event("Test event", "Test event", new EventType("Test event type", new EventKind("Fun")), organizer, matesDAO);

        User mate = mock(User.class);
        try {
            event.addMate(mate);
        } catch (RuntimeException e) { }

        verify(matesDAO, times(1)).addMateToEvent(event, mate);
        verify(matesDAO, never()).removeMateFromEvent(any(Event.class), any(User.class));
        verify(matesDAO, never()).getMatesByEvent(any(Event.class));
    }

    @Test
    public void TestRemoveMateFromEvent() {
        MatesDAO matesDAO = mock(MatesDAO.class);
        User organizer = mock(User.class);
        Event event = new Event("Test event", "Test event", new EventType("Test event type", new EventKind("Fun")), organizer, matesDAO);

        User mate = mock(User.class);
        try {
            event.removeMate(mate);
        } catch (RuntimeException e) { }

        verify(matesDAO, times(1)).removeMateFromEvent(event, mate);
        verify(matesDAO, never()).addMateToEvent(any(Event.class), any(User.class));
        verify(matesDAO, never()).getMatesByEvent(any(Event.class));
    }

    @Test
    public void TestGetMatesByEvent() {
        MatesDAO matesDAO = mock(MatesDAO.class);
        User organizer = mock(User.class);
        Event event = new Event("Test event", "Test event", new EventType("Test event type", new EventKind("Fun")), organizer, matesDAO);

        User user = mock(User.class);
        List<User> userList = Arrays.asList(user);
        when(matesDAO.getMatesByEvent(event)).thenReturn(userList);

        Assert.assertEquals(userList, event.getMates());
        verify(matesDAO, never()).removeMateFromEvent(any(Event.class), any(User.class));
        verify(matesDAO, never()).addMateToEvent(any(Event.class), any(User.class));
    }
}
