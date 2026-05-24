package at.spengergasse.views.prices;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Prices")
@Route("empty2")
@Menu(order = 2, icon = LineAwesomeIconUrl.EURO_SIGN_SOLID)
public class PricesView extends VerticalLayout {

    public PricesView() {
        setSpacing(false);

        H1 company = new H1("Steiner Pharmacy");
        company.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... Trusted care, fast service, better health ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        H2 pricelist = new H2("Unsere Preisgruppen");
        pricelist.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        H2 group1 = new H2("Schmerzmittel");
        Paragraph g1p1 = new Paragraph("Ibuprofen, Paracetamol, Aspirin");
        Paragraph g1p2 = new Paragraph("Schnelle Hilfe bei Schmerzen und Fieber");
        Paragraph g1p3 = new Paragraph("Preise ab 4,90 Euro");

        H2 group2 = new H2("Erkältung & Grippe");
        Paragraph g2p1 = new Paragraph("Hustensaft, Nasenspray, Halstabletten");
        Paragraph g2p2 = new Paragraph("Für schnelle Linderung bei Erkältungssymptomen");
        Paragraph g2p3 = new Paragraph("Preise ab 7,90 Euro");

        H2 group3 = new H2("Vitamine & Nahrungsergänzung");
        Paragraph g3p1 = new Paragraph("Vitamin C, Magnesium, Omega-3");
        Paragraph g3p2 = new Paragraph("Unterstützung für Immunsystem und Wohlbefinden");
        Paragraph g3p3 = new Paragraph("Preise ab 9,90 Euro");

        H2 group4 = new H2("Hautpflege & Dermatologie");
        Paragraph g4p1 = new Paragraph("Cremes, Salben, Sonnenschutz");
        Paragraph g4p2 = new Paragraph("Pflege für empfindliche und trockene Haut");
        Paragraph g4p3 = new Paragraph("Preise ab 12,90 Euro");

        H2 group5 = new H2("Rezeptpflichtige Medikamente");
        Paragraph g5p1 = new Paragraph("Antibiotika, Blutdruckmedikamente, Spezialpräparate");
        Paragraph g5p2 = new Paragraph("Nur mit ärztlicher Verschreibung erhältlich");
        Paragraph g5p3 = new Paragraph("Preis auf Anfrage");

        add(company, subName, pricelist,
                group1, g1p1, g1p2, g1p3,

                group2, g2p1, g2p2, g2p3,

                group3, g3p1, g3p2, g3p3,

                group4, g4p1, g4p2, g4p3,

                group5, g5p1, g5p2, g5p3);
    }

}
