package com.glucoclock.views.doctor;
import com.glucoclock.database.log_db.service.LogService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;

public class PatientInfo {
    private String firstName;
    private String lastName;
    private String email;



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

    public Button buildDeleteButton() {
        Button button = new Button("View Data");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(click->{
            button.getUI().ifPresent(ui->ui.navigate(ViewPatientsData.class));
            Notification.show(firstName+lastName);
        });
        return button;
    }

}
