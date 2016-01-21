package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Philipp on 17.01.16.
 */
@Named
@ApplicationScoped
public class ImageReader implements Serializable{

    /******* Variables *******/
    @Inject
    private FoodPlanerServiceDatabase foodPlanerService;

    /******** Methods ********/
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String imageId = context.getExternalContext().getRequestParameterMap().get("id");
            Recipe r = foodPlanerService.findById(Long.decode(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(r.getImage()));
        }
    }

}
