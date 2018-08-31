package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "EVENT_TYPE_ID")
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "ORGANIZER_ID")
    private User organizer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MATES",
               joinColumns = @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"),
               inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    private Set<User> mates;

    public Event() {
    }

    public Event(String name, String description, EventType eventType, User organizer) {
        this.name = name;
        this.description = description;
        this.eventType = eventType;
        this.organizer = organizer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<User> getMates() {
        return mates;
    }

    public void setMates(Set<User> mates) {
        this.mates = mates;
    }

    //может быть не в этом классе - добавляет user у которого isOrganizer = true
//    public void addMate(User user) {
//        if (user == null)
//            throw new IllegalArgumentException("User shouldn't be null.");
//
//        matesDAO.addMateToEvent(this, user);
//    }
//
//    public void removeMate(User user) {
//        if (user == null)
//            throw new IllegalArgumentException("User shouldn't be null.");
//
//        matesDAO.removeMateFromEvent(this, user);
//    }
}
