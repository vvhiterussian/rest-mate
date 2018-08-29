package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EVENT_KIND")
public class EventKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "eventKind", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventType> eventTypes;

    public EventKind() {
    }

    public EventKind(String name) {
        this.name = name;
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

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
}
