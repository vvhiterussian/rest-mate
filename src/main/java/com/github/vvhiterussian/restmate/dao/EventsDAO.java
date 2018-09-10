package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;
import java.util.Set;

public interface EventsDAO {
    List<Event> findEvents(EventKind eventKind, EventType eventType, String name);
    void addEvent(Event event);

    void addMate(Event event, User user);
    void removeMate(Event event, User user);
    Set<User> getMates(Event event);
}
