package de.thi.foodplaner.web.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 08.01.16.
 */
@Named
@SessionScoped
public class Home implements Serializable{

    /******* Variables *******/
    String searchText;

    /******** Methods ********/
    public String doSearch(){
        return "";
    }

    /***** Setter Getter *****/
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
