package com.github.vvhiterussian.restmate.model;

import javax.persistence.*;

@Entity
@Table(name = "EVENT_TYPES")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "EVENT_KIND_ID", nullable = false)
    private EventKind eventKind;

    public EventType() {
    }

    public EventType(String name, EventKind eventKind) {
        this.name = name;
        this.eventKind = eventKind;
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

    public EventKind getEventKind() {
        return eventKind;
    }

    public void setEventKind(EventKind eventKind) {
        this.eventKind = eventKind;
    }
}
