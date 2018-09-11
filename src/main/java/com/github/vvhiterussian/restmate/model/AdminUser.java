package com.github.vvhiterussian.restmate.model;

import com.github.vvhiterussian.restmate.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "admin")
public class AdminUser extends User {

    @Autowired
    @Transient
    private UsersDAO usersDAO;

    public AdminUser() {
    }

    public AdminUser(String login, String password, boolean isOrganizator) {
        super(login, password, isOrganizator);
    }
}
