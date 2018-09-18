package com.github.vvhiterussian.restmate.web.controllers;

import com.github.vvhiterussian.restmate.dao.UsersDAO;
import com.github.vvhiterussian.restmate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;

@Controller
public class UserController {
    // TODO разобраться с моделью между контроллерами
    @Autowired
    private UsersDAO usersDAO;

    @GetMapping(path = "/")
    public String index(ModelMap map) {
        map.put("isLoggedIn", false);
        return "index";
    }

    @GetMapping(path = "/users/register")
    public String register(ModelMap map) {
        map.put("isRegister", true);
        return "user-add";
    }

    @PostMapping(path = "/users/register")
    public String register(@RequestParam String login, @RequestParam String password, ModelMap map) {
        User newUser = new User(login, password, false);

        try {
            usersDAO.addUser(newUser);
        } catch (PersistenceException e) {
            // TODO прикрутить ошибки на форме
            map.put("errorMessage", e.getMessage());
            return "redirect:/users/register";
        }

        map.put("isLoggedIn", true);
        map.put("user", newUser);
        return "index";
    }

    @GetMapping(path = "/users/login")
    public String login(ModelMap map) {
        map.put("isRegister", false);
        return "user-add";
    }

    @PostMapping(path = "/users/login")
    public String login(@RequestParam String login, @RequestParam String password, ModelMap map) {
        User user = usersDAO.findByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                map.put("isLoggedIn", true);
                map.put("user", user);
                return "index";
            }
        }

        map.put("errorMessage", "Incorrect login or password");
        return login(map);
    }

    @GetMapping(path = "/users/logout")
    public String logout(ModelMap map) {
        map.put("isLoggedIn", false);
        map.put("user", null);
        return "index";
    }
}
