package com.github.vvhiterussian.restmate.web.modelviews;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;

public class EventsModelView {
    private User user;
    private List<Event> events;

    public EventsModelView(User user, List<Event> events) {
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
