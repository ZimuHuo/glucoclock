package com.glucoclock.views.patient;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.patient.ComprehensiveLogBookView;
import com.glucoclock.views.patient.IntensiveLogBookView;
import com.glucoclock.views.patient.PatientSignUp2;
import com.glucoclock.views.patient.SimpleLogBookView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@Route(value = "ConfirmationPage",layout = MainLayout.class)

public class ConfirmationPage extends VerticalLayout{
    private Button OKButton;
    private Icon Tick;
    private H1 Header ;

    public ConfirmationPage(){

        Header = new H1("Upload Successful!");

        Tick = new Icon(VaadinIcon.CHECK_CIRCLE_O);
        Tick.setSize("20%");
        //Tick.setColor("blue");
        OKButton = new Button("OK");
        OKButton .addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        OKButton .setWidth("30%");
        OKButton.addClickListener(e ->
                OKButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientStart.class)
                )
        );

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, Header,Tick,OKButton );

        add(Header,Tick,OKButton );
    }
}
