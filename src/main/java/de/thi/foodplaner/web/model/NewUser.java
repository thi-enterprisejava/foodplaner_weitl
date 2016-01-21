package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.security.Role;
import de.thi.foodplaner.domain.security.User;
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

    /******* Variables *******/
    private User user;
    @Inject
    UserServiceDatabase userService;

    /******* Constructor *******/
    @PostConstruct
    public void postConstruct() {
        this.user = new User();
    }

    /******* Methods *******/
    public String doAddUser() {
        this.user.setRole(Role.USER);
        this.userService.add(this.user);
        return "/home.xhtml";
    }

    /***** Setter Getter *****/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
