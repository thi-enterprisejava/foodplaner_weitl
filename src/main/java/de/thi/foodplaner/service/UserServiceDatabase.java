package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.security.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Philipp on 09.01.16.
 */
@Stateless
public class UserServiceDatabase implements Serializable{
    /******** Variables *******/
    private static final Logger LOGGER = LogManager.getLogger(UserServiceDatabase.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /********* Methods ********/
    @PermitAll
    public User add(User user) {
        LOGGER.info("Adding user to database");

        user.setPassword(hashingPassword(user.getPassword()));

        em.persist(user);

        return user;
    }

    private String hashingPassword(String password) {
        try{
            return Base64.getEncoder().encodeToString(
                    MessageDigest.getInstance("SHA-256").digest(password.getBytes()));
        }catch (NoSuchAlgorithmException e){
            LOGGER.error(e.getMessage());
        }
        return password;
    }

}