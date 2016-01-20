package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 09.01.16.
 */
@Stateless
public class UserServiceDatabase implements Serializable{
    /******** Variables *******/
    private static final Logger LOGGER = Logger.getLogger(UserServiceDatabase.class.getName());

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /******** Constructor *******/
    public UserServiceDatabase() {
        LOGGER.log(Level.INFO, "UserService created");
    }

    /********* Methods ********/
    public User add(User user) {
        LOGGER.log(Level.INFO, "Adding recipe to database");

        em.persist(user);

        return user;
    }
}