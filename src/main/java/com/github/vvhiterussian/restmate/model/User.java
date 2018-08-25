package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.EventsDAO;

public class User {

    private String login;
    private String password;
    private boolean isOrganizer;

    private EventsDAO eventsDAO;

    public User() {
    }

    public User(String login, String password, boolean isOrganizator, EventsDAO eventsDAO) {
        this.login = login;
        this.password = password;
        this.isOrganizer = isOrganizator;
        this.eventsDAO = eventsDAO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOrganizer() {
        return isOrganizer;
    }

    public void setOrganizer(boolean organizer) {
        isOrganizer = organizer;
    }

    public void addNewEvent(Event event) {
        if (!isOrganizer)
            throw new IllegalArgumentException("User should be organizer to add new events.");

        if (event == null)
            throw new IllegalArgumentException("Event shouldn't be null.");

        eventsDAO.addEvent(event);
    }
}
