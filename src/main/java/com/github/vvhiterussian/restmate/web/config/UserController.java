package com.github.vvhiterussian.restmate.web.config;

import com.github.vvhiterussian.restmate.dao.UsersDAO;
import com.github.vvhiterussian.restmate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UsersDAO usersDAO;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/users/register")
    public String register() {
        return "user-add";
    }

    @PostMapping(path = "/users/register")
    public String register(@RequestParam String login, @RequestParam String password) {
        User newUser = new User(login, password, false);
        usersDAO.addUser(newUser);

        return "index";
    }

    @GetMapping(path = "/users/login")
    public String login() {
        return "user-add"; // какие-то параметры в jsp, для разруливания логин или регистрация
    }

    @PostMapping(path = "/users/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        User user = usersDAO.findByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return "index";
            }
        }
        return "index";
    }
}
