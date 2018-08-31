package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.EventsDAO;
import com.github.vvhiterussian.restmate.dao.UsersDAO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "admin")
public class AdminUser extends User {

    @Transient
    private UsersDAO usersDAO;

    public AdminUser() {
    }

    public AdminUser(String login, String password, boolean isOrganizator, EventsDAO eventsDAO, UsersDAO usersDAO) {
        super(login, password, isOrganizator, eventsDAO);
        this.usersDAO = usersDAO;
    }
}
