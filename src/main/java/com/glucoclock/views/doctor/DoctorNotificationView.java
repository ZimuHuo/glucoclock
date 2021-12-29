package com.glucoclock.views.doctor;

import com.glucoclock.database.doctors_db.model.Doctor;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@PageTitle("Notifications")
@Route(value = "doctor/notifications")
public class DoctorNotificationView extends VerticalLayout {
    private Grid<Notification> grid;
    private ListDataProvider<Notification> dataProvider;

    private Grid.Column<Notification> firstNameColumn;
    private Grid.Column<Notification> lastNameColumn;
    private Grid.Column<Notification> dateColumn;
    private Grid.Column<Notification> requestTypeColumn;
    private Grid.Column<Notification> statusColumn;
    private Grid.Column<Notification> buttonColumn;

    private MenuBar menu = new MenuBar("DNS");

    private H3 title = new H3("Notifications");

    public DoctorNotificationView(){

    }
}
