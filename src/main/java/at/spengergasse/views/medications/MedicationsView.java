package at.spengergasse.views.medications;

import at.spengergasse.domain.PharMed;
import at.spengergasse.domain.PharMedException;
import at.spengergasse.service.PharMedService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.notification.Notification;

import java.time.LocalDate;

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
    private final Button ButtonAdd1Order = new Button("Add Order");
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
        ButtonAdd1Order.addClickListener(event-> addEditOrder(null));

        add(new HorizontalLayout(ButtonRemoveAllMedications, ButtonAdd10Medications, ButtonIncreasePrice,ButtonRemoveRezeptflicht, ButtonAddWrong, ButtonAdd1Order));

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
        grid.addComponentColumn(med -> {
            Button delete = new Button("delete");
            delete.addClickListener(e -> removeMedId(med.getMedicationId()));
            return delete;
        })
                .setHeader("Action")
                .setSortable(false);

        grid.addComponentColumn(med-> {
            Button add1Med = new Button("Add 1 Med");
            add1Med.addClickListener(e-> add1Med(med.getMedicationId()));
            return add1Med;
        })
                .setHeader("Action")
                .setSortable(false);

        grid.addComponentColumn(med ->
        {
            Button editMed = new Button("Edit");
            editMed.addClickListener(e-> addEditOrder(med));
            return editMed;
        })
                .setHeader("Action")
                .setSortable(false);

        add(grid);
        reload();

    }

    private void addEditOrder(PharMed existingOrder)
    {
        Dialog dialog;

        dialog =new Dialog();
        PharMed pharMed;
        if(existingOrder == null)
        {
            dialog.setHeaderTitle(" Add 1 Order");
            pharMed = new PharMed();
        }
        else
        {
            dialog.setHeaderTitle(" Edit Order");
            pharMed = existingOrder;
        }


        TextField medicationID = new TextField("Medication ID");
        DatePicker orderDate = new DatePicker("Order Date");
        TextField supplierName = new TextField("Supplier Name");
        ComboBox medicationType = new ComboBox("Medication Type");
        medicationType.setItems("Painkiller","Antibiotics","Anti-allergy" , "Nasal Spray");
        NumberField price = new NumberField("Price");
        IntegerField stockQuantity = new IntegerField("Stock Quantity");
        Checkbox rezeptFrei = new Checkbox("Rezeptfrei");

        BeanValidationBinder<PharMed> binder = new BeanValidationBinder<>(PharMed.class);

        //Do not bind ID Field!!!
        binder.forField(orderDate)
                .bind("orderDate");

        binder.forField(supplierName)
                .bind("supplierName"); //bind(String propertyName) nutzt Reflection, um automatisch den passenden Getter und Setter der Bean zu finden. Vaadin sucht dann nach getOrderDate() / setOrderDate() in der gebundenen Klasse (z.B. Order).
        binder.forField(medicationType)
                .bind("medicationType");
        binder.forField(price)
                .bind("price");
        binder.forField(stockQuantity)
                .bind("stockQuantity");
        binder.forField(rezeptFrei)
                .bind("rezeptFrei");



        binder.setBean(pharMed);

        medicationID.setValue(""+pharMed.getMedicationId());
        medicationID.setReadOnly(true);

        VerticalLayout formLayout = new VerticalLayout(
                medicationID,
                orderDate,
                supplierName,
                medicationType,
                price,
                stockQuantity,
                rezeptFrei
        );

        Button buttonOk = new Button("ok");
        Button buttonCancel = new Button("cancel");

        buttonCancel.addClickListener(event-> dialog.close());
        buttonOk.addClickListener(event ->
        {
            try
            {
                if(binder.validate().isOk() ==true)
                {
                    if(existingOrder == null)
                    {
                        PharMedService.add1Order(pharMed);
                    }
                    dialog.close();
                    reload();

                    if(existingOrder == null)
                    {
                        Notification.show("One Medication added");
                    }
                    else
                    {
                        Notification.show("One Medication modified");
                    }
                }
                else
                {
                    Notification.show("Check your input!");
                }
            }
            catch (PharMedException e)
            {
                Notification.show(e.getMessage());
            }
        });

        dialog.add(formLayout);
        dialog.getFooter().add(buttonOk,buttonCancel);
        dialog.open();

    }


    private void add1Med(Long medicationId)
    {
        try
        {
            PharMedService.add1Med(medicationId);
            reload();
        }

        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }


    }

    private void removeMedId(Long medicationId)
    {
        try
        {
            PharMedService.removeMedId(medicationId);
            reload();
        }
        catch (PharMedException e)
        {
            Notification.show(e.getMessage());
        }
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
