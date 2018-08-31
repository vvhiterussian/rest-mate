package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;

import java.util.List;

public interface EventTypesDAO {

    List<EventType> getEventTypes();
    List<EventType> searchEventTypes(EventKind eventKind); // +поиск по тегам
    void addEventType(EventType eventType);

}
