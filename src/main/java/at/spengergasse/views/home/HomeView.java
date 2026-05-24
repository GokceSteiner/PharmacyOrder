package at.spengergasse.views.home;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import static com.vaadin.copilot.shaded.io.netty.handler.codec.http.HttpHeaders.getHeader;

@PageTitle("Home")
@Route("home")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class HomeView extends VerticalLayout {

    public HomeView()
    {
        setSpacing(false);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

       VerticalLayout header = getHeader();
        HorizontalLayout logoText = new HorizontalLayout();


        Image logo = new Image("images/logo.png", "Steiner Pharmacy Logo");
        logo.setWidth("400px");

        Paragraph line1 = new Paragraph("Willkommen bei Steiner Pharmacy – Ihrer vertrauensvollen Apotheke für Gesundheit, Wohlbefinden und persönliche Beratung. Unser Ziel ist es, Sie mit hochwertigen pharmazeutischen Produkten, kompetenter Betreuung und einem zuverlässigen Service bestmöglich zu unterstützen. Ihre Gesundheit steht bei uns an erster Stelle.");

        line1.setWidth("500px");
        line1.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        logoText.add(logo, line1);

        Paragraph line2 = new Paragraph("Bei Steiner Pharmacy verbinden wir moderne pharmazeutische Versorgung mit persönlicher Betreuung. Ob Medikamente, Gesundheitsprodukte oder individuelle Beratung – unser engagiertes Team steht Ihnen mit Fachwissen und Sorgfalt zur Seite. Wir legen großen Wert auf Qualität, Sicherheit und ein angenehmes Kundenerlebnis.");

        line2.setWidth("500px");
        line2.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("margin-top", "30px")
                .set("text-align", "left");


        Paragraph line3 = new Paragraph("Als Ihre moderne Apotheke möchten wir Gesundheit einfach und zugänglich machen. Mit einem benutzerfreundlichen Service, schnellen Lösungen und einem starken Fokus auf Ihre Bedürfnisse ist Steiner Pharmacy Ihr zuverlässiger Partner für ein gesundes Leben – heute und in Zukunft.");

        line3.setWidth("500px");
        line3.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

        HorizontalLayout address = new HorizontalLayout();
        H3 name = new H3("Steiner Pharmacy");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        address.add(name,street,city);

        add(header, logoText, line2, line3, address);

    }

    public static VerticalLayout getHeader()
    {
        VerticalLayout header;

        header = new VerticalLayout();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setWidthFull();


        H1 company = new H1("Steiner Pharmacy");
        company.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2("... Trusted care, fast service, better health ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        header.add(company, subName);

        return header;
    }
}
