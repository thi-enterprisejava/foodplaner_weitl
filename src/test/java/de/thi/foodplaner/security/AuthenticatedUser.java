package de.thi.foodplaner.security;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

/**
 * Created by Philipp on 21.01.16.
 */
@Stateless
@RunAs("user")
@PermitAll
public class AuthenticatedUser {

    public void call(Callable callable) throws Exception {
        callable.call();
    }

    public void run(Runnable runnable) throws Exception {
        runnable.run();
    }

}
