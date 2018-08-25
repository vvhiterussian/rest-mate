package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.MatesDAO;

import java.util.List;

public class Event {

    private String name;
    private String description;
    private EventType eventType;
    private User organizer;

    private MatesDAO matesDAO;

    public Event() {
    }

    public Event(String name, String description, EventType eventType, User organizer, MatesDAO matesDAO) {
        this.name = name;
        this.description = description;
        this.eventType = eventType;
        this.organizer = organizer;
        this.matesDAO = matesDAO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<User> getMates() {
        return matesDAO.getMatesByEvent(this);
    }

    //может быть не в этом классе - добавляет user у которого isOrganizer = true
    public void addMate(User user) {
        if (user == null)
            throw new IllegalArgumentException("User shouldn't be null.");

        matesDAO.addMateToEvent(this, user);
    }

    public void removeMate(User user) {
        if (user == null)
            throw new IllegalArgumentException("User shouldn't be null.");

        matesDAO.removeMateFromEvent(this, user);
    }
}
