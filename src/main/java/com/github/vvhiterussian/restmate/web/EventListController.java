package com.github.vvhiterussian.restmate.web;

import com.github.vvhiterussian.restmate.dao.EventsDAO;
import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;
import com.github.vvhiterussian.restmate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventListController {

    @Autowired
    EventsDAO eventsDAO;

    @GetMapping(path = "/events/all")
    public String getEvents(ModelMap model) {
        List<Event> events = new ArrayList<>();
        EventsListBean eventsListBean = new EventsListBean("Man", events);

        events.add(new Event(
                "test-event",
                "test-event-description",
                new EventType("test-event-type", new EventKind("fun")),
                new User("login", "pass", false, null)));

        model.put("name", "man");
        model.put("eventsListBean", eventsListBean);

        return "event-list";
    }

    @GetMapping(path = "/events/add")
    public String addEventAction() {
        return "events-add";
    }

    @PostMapping(path = "/events/add")
    public String addEvent(@RequestParam String name) {
        //add

        return "event-list";
    }
}
