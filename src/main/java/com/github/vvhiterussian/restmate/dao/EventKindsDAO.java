package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.EventKind;

import java.util.List;

public interface EventKindsDAO {
    void addEventKind(EventKind eventKind);
    List<EventKind> getEventKinds();
}
