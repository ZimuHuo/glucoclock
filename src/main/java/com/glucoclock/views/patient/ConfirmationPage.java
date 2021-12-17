package com.glucoclock.views.patient;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.patient.ComprehensiveLogBookView;
import com.glucoclock.views.patient.IntensiveLogBookView;
import com.glucoclock.views.patient.PatientSignUp2;
import com.glucoclock.views.patient.SimpleLogBookView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("Confirmation Page")
@Route(value = "ConfirmationPage")

public class ConfirmationPage extends VerticalLayout{
    private Button OKButton;
    //private Icon Tick;
    private H2 Header ;
    private MenuBar menu = new MenuBar("PNS");


    public ConfirmationPage(){

        Image tick = new Image("images/check.png","Upload Successful");
        tick.setHeight("160px");

        Header = new H2("Upload Successful!");

        OKButton = new Button("OK");
        OKButton .addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        OKButton .setWidth("15%");
        OKButton.setHeight("40px");
        OKButton.addClickListener(e ->
                OKButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientStart.class)
                )
        );


        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, Header,tick,OKButton );

        add(menu, Header,tick,OKButton);
    }
}
