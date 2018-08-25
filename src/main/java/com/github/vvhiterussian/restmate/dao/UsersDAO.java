package com.github.vvhiterussian.restmate.dao;

public interface UsersDAO {
    void addUser(String login, String password, boolean isOrganizator);
}
