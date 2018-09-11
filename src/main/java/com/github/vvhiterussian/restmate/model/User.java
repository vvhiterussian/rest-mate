package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.EventsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column
    private boolean isOrganizer;

    @ManyToMany(mappedBy = "mates", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Event> userEvents;

    @Autowired
    @Transient
    private EventsDAO eventsDAO;

    public User() {
    }

    public User(String login, String password, boolean isOrganizator) {
        this.login = login;
        this.password = password;
        this.isOrganizer = isOrganizator;
//        this.eventsDAO = eventsDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public void addNewEvent(Event event) {
        if (!isOrganizer)
            throw new IllegalArgumentException("User should be organizer to add new events.");

        if (event == null)
            throw new IllegalArgumentException("Event shouldn't be null.");

        eventsDAO.addEvent(event);
    }
}
