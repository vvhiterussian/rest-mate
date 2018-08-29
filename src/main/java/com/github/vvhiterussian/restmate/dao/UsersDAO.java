package com.github.vvhiterussian.restmate.dao;

import com.github.vvhiterussian.restmate.model.User;

public interface UsersDAO {
    User findByLogin(String login);
    void addUser(User user);
}
