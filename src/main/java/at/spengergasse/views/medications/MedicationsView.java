package at.spengergasse.views.medications;

import at.spengergasse.domain.PharMed;
import at.spengergasse.service.PharMedService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Medications")
@Route("medications")
@Menu(order = 1, icon = LineAwesomeIconUrl.PILLS_SOLID)
public class MedicationsView extends VerticalLayout {

    private final Grid<PharMed> grid = new Grid<>(PharMed.class, true); //nimm alle eigensachaften von klasse PharMed und erstell mir
    private final PharMedService pharMedService;

    public MedicationsView(@Autowired PharMedService pharMedService) {


        this.pharMedService = pharMedService; //auf meiner Webseite wird meine Service Klasse automatisch instanziert

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        add(grid);

        reload();

    }

    private void reload() //wenn in Collection die Datei sich ändert, neu reloaden
    {
        grid.setItems(PharMedService.findAll());
    }

}
