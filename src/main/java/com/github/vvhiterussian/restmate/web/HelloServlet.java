package com.github.vvhiterussian.restmate.web;

import com.github.vvhiterussian.restmate.model.Event;
import com.github.vvhiterussian.restmate.model.EventKind;
import com.github.vvhiterussian.restmate.model.EventType;
import com.github.vvhiterussian.restmate.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/users/*")
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Event> events = new ArrayList<>();
        EventsListBean eventsListBean = new EventsListBean("Man", events);

        events.add(new Event(
                "test-event",
                "test-event-description",
                new EventType("test-event-type", new EventKind("fun")),
                new User("login", "pass", false, null)));

        req.setAttribute("name", "man");
        req.setAttribute("eventsListBean", eventsListBean);

        req.getRequestDispatcher("/pages/event-list.jsp")
                .forward(req, resp);
    }
}
