package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.Role;
import de.thi.foodplaner.domain.User;
import de.thi.foodplaner.service.UserServiceDatabase;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 14.01.16.
 */
@Named
@ViewScoped
public class NewUser implements Serializable {

    private User user;

    UserServiceDatabase userService;

    @Inject
    public NewUser(UserServiceDatabase userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct() {
        this.user = new User();
    }

    public String doAddUser() {
        this.user.setRole(Role.user);
        this.userService.add(this.user);
        return "/home.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
