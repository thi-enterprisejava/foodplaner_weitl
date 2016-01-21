package de.thi.foodplaner.web.model;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 08.01.16.
 */
@Named
@SessionScoped
public class Home implements Serializable{


    public void logout() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.setResponseStatus(401);
        externalContext.getResponseOutputWriter().write("<html><head><meta http-equiv='refresh' content='0;home.xhtml'></head></html>");
        facesContext.responseComplete();
    }

}
