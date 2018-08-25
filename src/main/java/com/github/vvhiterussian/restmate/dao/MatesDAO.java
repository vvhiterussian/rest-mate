package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.User;

import java.util.List;

public interface MatesDAO {
    List<User> getMatesByEvent(Event event);
    void addMateToEvent(Event event, User user);
    void removeMateFromEvent(Event event, User user);
}
