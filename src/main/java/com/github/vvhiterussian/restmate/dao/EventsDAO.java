package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;

import java.util.List;

public interface EventsDAO {
    List<Event> findEvents(EventKind eventKind, EventType eventType, String name);
    void addEvent(Event event);
}
