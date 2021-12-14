package com.glucoclock.views.patient;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@PageTitle("Patient Start Page")
@Route(value = "PatientStart",layout = MainLayout.class)

public class PatientStart extends VerticalLayout{
    private ComboBox<String> LBtybe;
    private DatePicker datePicker;
    private Icon update;
    private Button updateButton;
    private MenuBar menu = new MenuBar("PStart");
    public PatientStart(){
        String Logbook;
        add(menu);
//        Image graph = new Image("images/bgl.png","Plot");
//        graph.setWidth("80%");

        LBtybe = new ComboBox<>();
        LBtybe.setLabel("Logbook\nType");
        LBtybe.setItems("Simple","Comprehensive","Intensive");
        LBtybe.addCustomValueSetListener(
                event -> LBtybe.setValue(event.getDetail()));

        datePicker = new DatePicker("Date");
        Locale finnishLocale = new Locale("fi", "FI");
        datePicker.setLocale(finnishLocale);
        datePicker.setValue(LocalDate.now(ZoneId.systemDefault()));

        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateButton = new Button(update);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidth("150px");
        updateButton.setHeight("150px");

        LBtybe.addValueChangeListener(event -> {
            if (event.getValue() == "Simple") {
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(SimpleLogBookView.class)
                        )
                );
            } else if (event.getValue() == "Comprehensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(ComprehensiveLogBookView.class)
                        )
                );
            }
            else if (event.getValue() == "Intensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(IntensiveLogBookView.class)
                        )
                );
            }
            //System.out.println(event.getValue());;
        });
        //setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER,LBtybe,datePicker,updateButton);

        add(LBtybe,datePicker,updateButton);

    }

}
