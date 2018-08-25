package com.github.vvhiterussian.restmate.model;

public class EventType {
    private String name;
    private EventKind eventKind;

    public EventType() {
    }

    public EventType(String name, EventKind eventKind) {
        this.name = name;
        this.eventKind = eventKind;
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
