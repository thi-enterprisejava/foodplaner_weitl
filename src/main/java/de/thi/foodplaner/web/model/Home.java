package de.thi.foodplaner.web.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Philipp on 08.01.16.
 */
@Named
@SessionScoped
public class Home implements Serializable{

    /******* Variables *******/

    private static final Logger LOGGER = LogManager.getLogger(Home.class);

    /******** Methods ********/

    public void logout()  {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.setResponseStatus(401);
        try{
            externalContext.getResponseOutputWriter().write("<html><head><meta http-equiv='refresh' content='0;home.xhtml'></head></html>");

        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }
        facesContext.responseComplete();
    }

}
