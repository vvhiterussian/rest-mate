package com.github.vvhiterussian.restmate.web;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;

public class EventsListBean {
    private User user;
    private List<Event> events;

    public EventsListBean(User user, List<Event> events) {
        this.user = user;
        this.events = events;
    }

    public User getUser() {
        return user;
    }

    public List<Event> getEvents() {
        return events;
    }
}
