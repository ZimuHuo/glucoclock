package com.glucoclock.views.doctor;

import com.glucoclock.database.doctorpatient_db.service.DoctorPatientService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.server.VaadinSession;

import java.util.UUID;

public class PatientInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String suggestedLbType;
    private UUID uid;
    private final DoctorPatientService doctorPatientService;


//    public PatientInfo(String firstName, String lastName, String email) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }

    public PatientInfo(String firstName, String lastName, String email, String suggestedLbType, UUID uid, DoctorPatientService doctorPatientService) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.suggestedLbType = suggestedLbType;
        this.uid = uid;
        this.doctorPatientService = doctorPatientService;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuggestedLbType() {
        return suggestedLbType;
    }

    public void setSuggestedLbType(String suggestedLbType) {
        this.suggestedLbType = suggestedLbType;
    }

    public Select<String> buildLogbookMenu(){
        Select<String> logbookSuggestion = new Select<>();
        logbookSuggestion.setItems("Simple","Comprehensive","Intensive");
        logbookSuggestion.setValue(suggestedLbType);
        logbookSuggestion.setEmptySelectionAllowed(true);
        return logbookSuggestion;
    }

    public Button buildEditLbTypeButton(){
        Icon e = new Icon(VaadinIcon.EDIT);
        Button edit = new Button(e);
        edit.addClickListener(click->{
            VaadinSession.getCurrent().setAttribute("PatientUID", uid);
            edit.getUI().ifPresent(ui->ui.navigate(SuggestLogbookTypeView.class));
        });
        return edit;
    }



    public Button buildViewButton() {
        Button button = new Button("View Data");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click->{
            VaadinSession.getCurrent().setAttribute("PatientID", uid);
            VaadinSession.getCurrent().setAttribute("PatientName", firstName+" "+lastName);
            button.getUI().ifPresent(ui->ui.navigate(PatientDataView.class));
            Notification.show(firstName+lastName);
        });
        return button;
    }

    public Button deletePatient(){
        Button deletePatient = new Button("Delete");
        deletePatient.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        deletePatient.addClickListener(click->{
            doctorPatientService.deletePatient(uid);
            Notification.show("Delete "+firstName+" "+lastName);

            UI.getCurrent().getPage().reload();

        });
        return deletePatient;
    }


}
