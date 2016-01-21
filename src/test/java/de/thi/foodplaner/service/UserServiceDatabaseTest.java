package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.security.Role;
import de.thi.foodplaner.domain.security.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 * Created by Philipp on 21.01.16.
 */
@RunWith(Arquillian.class)
public class UserServiceDatabaseTest {

    @EJB
    UserServiceDatabase userService;


    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(UserServiceDatabase.class)
                .addClass(User.class)
                .addClass(Role.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("cocktailtest-ds.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }


    @Test
    public void thatUserCanBeAdded() throws Exception {
        User user = new User();
        user.setNickname("TestName");
        user.setPassword("TestPassword");
        user.setRole(Role.USER);

        userService.add(user);
    }

}
