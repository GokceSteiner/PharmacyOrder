package at.spengergasse.views.medications;

import at.spengergasse.domain.PharMed;
import at.spengergasse.domain.PharMedException;
import at.spengergasse.service.PharMedService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.notification.Notification;

import static at.spengergasse.service.PharMedService.pharMeds;
import static javax.management.Notification.*;

@PageTitle("Medications")
@Route("medications")
@Menu(order = 1, icon = LineAwesomeIconUrl.PILLS_SOLID)
public class MedicationsView extends VerticalLayout {

    private final Button ButtonRemoveAllMedications = new Button("Remove All");
    private final Button ButtonAdd10Medications = new Button("Add 10");
    private  final Button ButtonIncreasePrice = new Button("Increase Price");
    private final Button ButtonRemoveRezeptflicht = new Button("Remove Rezetpflicht");
    private final Button ButtonAddWrong = new Button("Add Wrong");
    private final Grid<PharMed> grid = new Grid<>(PharMed.class, false); //nimm alle eigensachaften von klasse PharMed und erstell mir
    private final PharMedService pharMedService;

    public MedicationsView(@Autowired PharMedService pharMedService) {


        this.pharMedService = pharMedService; //auf meiner Webseite wird meine Service Klasse automatisch instanziert

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        ButtonRemoveAllMedications.addClickListener(e-> removeAllMedications());
        ButtonAdd10Medications.addClickListener(e->add10Medications());
        ButtonIncreasePrice.addClickListener(e -> increasePrice());
        ButtonRemoveRezeptflicht.addClickListener(e-> removeRezeptpflicht());
        ButtonAddWrong.addClickListener(e-> addWrong());

        add(new HorizontalLayout(ButtonRemoveAllMedications, ButtonAdd10Medications, ButtonIncreasePrice,ButtonRemoveRezeptflicht, ButtonAddWrong));

        grid.addColumn(med-> med.getMedicationId())
                .setHeader("Medication ID")
                .setSortable(true);
        grid.addColumn(med -> med.getMedicationType())
                .setHeader("Medication Type")
                .setSortable(true);
        grid.addColumn(med -> med.getOrderDate())
                .setHeader("Order Date")
                .setSortable(true);
        grid.addColumn(med -> med.getPrice())
                .setHeader("Price")
                .setSortable(true);

        Image l = new Image("icons/rezept.png", "type");
        l.setWidth("22px");
        HorizontalLayout headerType = new HorizontalLayout(l, new Span("type"));


        grid.addColumn(med -> (med.getRezeptFrei() == true)? "rezeptfrei" : "rezeptpflichtig")
                .setHeader(headerType)
                .setSortable(true);


        grid.addColumn(med-> med.getStockQuantity())
                .setHeader("Stock Quantity")
                .setSortable(true);
        grid.addColumn(med -> med.getSupplierName())
                .setHeader("Supplier Name")
                .setSortable(true);
        grid.addComponentColumn( med ->
        {
            Checkbox rezept = new Checkbox(med.getRezeptFrei());
            rezept.setReadOnly(true);
            return rezept;
        })
                .setHeader("no-Prescription")
                .setSortable(true);

        add(grid);
        reload();

    }

    private void addWrong()
    {

        try
        {
            PharMedService.addWrong();
            reload();
        }
        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void removeRezeptpflicht()
    {
        try
        {
            PharMedService.removeRezeptpflicht();
            reload();
        }
        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }

    }

    private void increasePrice()
    {
        try
        {
            PharMedService.increasePrice();
            reload();
        }
        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void add10Medications()
    {
        try
        {
            PharMedService.add10Medication();
            ButtonRemoveAllMedications.setEnabled(true);
            ButtonRemoveRezeptflicht.setEnabled(true);
            reload();
        }
        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void removeAllMedications()
    {
        try
        {
            PharMedService.removeAllMedications();
            ButtonRemoveAllMedications.setEnabled(false);
            ButtonRemoveRezeptflicht.setEnabled(false);
            reload();
        }
       catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }
    }

    private void reload() //wenn in Collection die Datei sich ändert, neu reloaden
    {
        grid.setItems(PharMedService.findAll());
    }

}
