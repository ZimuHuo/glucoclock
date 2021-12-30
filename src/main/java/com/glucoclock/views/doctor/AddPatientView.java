package com.glucoclock.views.doctor;

import com.glucoclock.views.MenuBar;
import com.glucoclock.views.doctor.AddPatientView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add Patient")
@Route(value = "doctor/add-patient")
public class AddPatientView extends Div {
    private H2 title = new H2("Add Patient");
    private TextField patientEmail = new TextField("Enter Patient Email");
    private Icon searchIcon = new Icon(VaadinIcon.SEARCH);
    private Button search = new Button(searchIcon);
    private Button add = new Button("Add Patient");
    private MenuBar menu = new MenuBar("DNS");

    public AddPatientView(){
        //buttons style
        searchIcon.setColor("white");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //Patient found notification upon click
        search.addClickListener(e->Notification.show("Patient Found").addThemeVariants(NotificationVariant.LUMO_SUCCESS));

        //Click add to go back to home page
        add.addClickListener(e->add.getUI().ifPresent(ui ->
                ui.navigate(DoctorStartView.class))
        );

        //Search for patient row
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(patientEmail,search);
        hl.setAlignItems(FlexComponent.Alignment.BASELINE);

        //Page layout
        VerticalLayout vl = new VerticalLayout();
        vl.add(title, hl, add);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        add(menu,vl);
    }
}
