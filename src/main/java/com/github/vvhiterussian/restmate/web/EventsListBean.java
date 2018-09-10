package com.github.vvhiterussian.restmate.web;

import com.github.vvhiterussian.restmate.model.Event;

import java.util.List;

public class EventsListBean {
    private String name;
    private List<Event> events;

    public EventsListBean(String name, List<Event> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
