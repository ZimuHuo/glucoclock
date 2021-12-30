package com.glucoclock.views.patient;

import com.glucoclock.database.log_db.service.LogService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@PageTitle("Start Page")
@Route(value = "/patient/start-page")
@RolesAllowed("PATIENT")
public class PatientStart extends VerticalLayout{
    private ComboBox<String> LBtybe;
    private DatePicker datePicker;
    private Icon update;
    private Button updateButton;
    private MenuBar menu = new MenuBar("PStart");
    private H2 title = new H2("Upload Logbook Entry");
    public PatientStart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        authentication.getName();
        System.out.print(authentication.getName()); // here it gets the username. absolutely fantastic
        //way easier than what im expected how cool is that!

        String Logbook;
        add(menu);

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
        updateButton.setWidth("120px");
        updateButton.setHeight("120px");

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
        setHorizontalComponentAlignment(Alignment.CENTER,title,LBtybe,datePicker,updateButton);

        add(title,LBtybe,datePicker,updateButton);

    }

}
