package com.github.vvhiterussian.restmate.web.controllers;

import com.github.vvhiterussian.restmate.dao.OrganizerStatusRequestsDAO;
import com.github.vvhiterussian.restmate.dao.UsersDAO;
import com.github.vvhiterussian.restmate.model.OrganizerStatusRequest;
import com.github.vvhiterussian.restmate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.PersistenceException;

@Controller
public class OrganizerRequestController {

    @Autowired
    OrganizerStatusRequestsDAO organizerStatusRequestsDAO;

    @Autowired
    UsersDAO usersDAO;

    @PostMapping(path = "/organizer-requests/add")
    public String add(User user, ModelMap map) {
        try {
//            User candidate = usersDAO.getUserById(candidateId);
            OrganizerStatusRequest request = new OrganizerStatusRequest(user);
            organizerStatusRequestsDAO.addRequest(request);
        } catch (PersistenceException e) {
            map.put("errorMessage", e.getMessage());
        } catch (RuntimeException e) {
            map.put("errorMessage", e.getMessage());
        }

        // TODO стираются данные из мапы isLoggedIn, user
        return "redirect:/";
    }
}
