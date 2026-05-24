package at.spengergasse.views.prices;

import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Prices")
@Route("prices")
@Menu(order = 2, icon = LineAwesomeIconUrl.EURO_SIGN_SOLID)
public class PricesView extends VerticalLayout {

    public PricesView() {
        setSpacing(false);

        VerticalLayout header = HomeView.getHeader();

        H2 pricelist = new H2("Unsere Preisgruppen");
        pricelist.getStyle()
                .set("margin", "0")
                .set("color", "gray");


        FlexLayout groupsHor = new FlexLayout();

        VerticalLayout group1 = getCard("Schmerzmittel", 8.60,4.25,10.50);
        VerticalLayout group2 = getCard("Erkältung & Grippe", 23.50,11.25, 28.99);
        VerticalLayout group3 = getCard("Vitamine & Nahrungsergänzung", 35.25,18.70, 36.50);
        VerticalLayout group4 = getCard("Hautpflege & Dermatologie", 13.25, 7.99, 15.25);
        VerticalLayout group5 = getCard("Pharmazeutische Spezialprodukte", 125.00, 75.45, 150.00);

        groupsHor.setWidthFull();
        groupsHor.setJustifyContentMode(JustifyContentMode.CENTER);
        groupsHor.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        groupsHor.add(group1, group2, group3, group4, group5);


        Paragraph info = new Paragraph("Inklusive Steuer");

        add(header, pricelist, groupsHor, info);
    }

    public VerticalLayout getCard(String titleText, double ohneRezept, double mitRezept, double nachtDienst)
    {
        VerticalLayout group = new VerticalLayout();
        H2 groupName = new H2(titleText);
        Paragraph preisOhneRezept = new Paragraph("Preis ohne Rezept " + ohneRezept + " €");
        Paragraph preisMitRezept = new Paragraph("Preis mit Rezept " + mitRezept + " €");
        Paragraph preisNachtDienst = new Paragraph("Preis Nachdienst " + nachtDienst +" €");

        group.add(groupName, preisOhneRezept, preisMitRezept, preisNachtDienst);

        group.setWidth("350px");
        group.setPadding(true);
        group.setSpacing(false);
        group.getStyle()
                .set("border", "1px solid lightgray")
                .set("border-radius", "10px")
                .set("margin", "10px");

        return group;

    }

}
