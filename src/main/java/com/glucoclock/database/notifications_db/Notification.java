package com.glucoclock.database.notifications_db;

import com.glucoclock.views.doctor.ViewPatientsData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;

import java.time.LocalDate;

public class Notification {
    private String patientFirstName;
    private String patientLastName;
    private LocalDate date;
    private String requestType;
    private String status;

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Span buildStatusBadge(){
        Span statusBadge = new Span(status);
        if (status == "Unresolved"){
            statusBadge.getElement().getThemeList().add("badge error");
        }
        else{
            statusBadge.getElement().getThemeList().add("badge success");
        }
        return statusBadge;
    }

    public Button buildViewButton() {
        Button button = new Button("View Details");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        button.addClickListener(click->{
//            button.getUI().ifPresent(ui->ui.navigate(ViewPatientsData.class)); //change navigation class
//            com.vaadin.flow.component.notification.Notification.show(patientFirstName+patientLastName+date+requestType+status);
//        });
        return button;
    }
}
