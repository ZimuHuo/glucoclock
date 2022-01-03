package com.glucoclock.views.doctor;

import com.glucoclock.views.MenuBar;
import com.glucoclock.database.notifications_db.Notifications;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@PageTitle("Notifications")
@Route(value = "doctor/notifications")
public class DoctorNotificationView extends VerticalLayout {
    private Grid<Notifications> grid;
    private ListDataProvider<Notifications> dataProvider;

    private Grid.Column<Notifications> firstNameColumn;
    private Grid.Column<Notifications> lastNameColumn;
    private Grid.Column<Notifications> dateColumn;
    private Grid.Column<Notifications> requestTypeColumn;
    private Grid.Column<Notifications> statusColumn;
    private Grid.Column<Notifications> buttonColumn;

    private MenuBar menu = new MenuBar("DNS");

    private H3 title = new H3("Notifications");

    public DoctorNotificationView(){
        VerticalLayout vl = new VerticalLayout();
        setSizeFull();
        createGrid();
        vl.add(title);
        vl.setPadding(true);
        vl.setMargin(true);
        vl.setAlignItems(Alignment.CENTER);
        add(vl,grid,menu);
        setPadding(true);
        setMargin(true);

    }
    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(getNotifications());
        grid.setDataProvider(dataProvider);
    }


    private void addColumnsToGrid() {
        firstNameColumn = grid.addColumn(Notifications::getPatientFirstName, "FirstName").setHeader("Patient First Name").setWidth("18%").setFlexGrow(0);
        lastNameColumn = grid.addColumn(Notifications::getPatientLastName, "LastName").setHeader("Patient Last Name").setWidth("18%").setFlexGrow(0);
        dateColumn = grid.addColumn(Notifications::getDate, "Date").setHeader("Date").setWidth("18%").setFlexGrow(0);
        requestTypeColumn = grid.addColumn(Notifications::getRequestType,"RequestType").setHeader("Request Type").setWidth("18%").setFlexGrow(0);
        statusColumn = grid.addComponentColumn(Notifications::buildStatusBadge).setHeader("Status").setWidth("18%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(Notifications::buildViewButton).setWidth("15%").setFlexGrow(0);
    }



    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder("Filter");
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(notification -> StringUtils.containsIgnoreCase(notification.getPatientFirstName(), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Filter");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(notification -> StringUtils.containsIgnoreCase(notification.getPatientLastName(), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

//        Select<String> statusFilter = new Select<>();
//        statusFilter.setEmptySelectionAllowed(true);
//        statusFilter.setItems("Unresolved");
//        statusFilter.setPlaceholder("Filter");
//        statusFilter.setWidth("100%");
//        statusFilter.addValueChangeListener(event -> dataProvider
//                .addFilter(notification -> StringUtils.containsIgnoreCase(notification.getStatus(), statusFilter.getValue())));
//        if (statusFilter.getValue() == "Unresolved"){filterRow.getCell(statusColumn).setComponent(statusFilter);}
    }


    private List<Notifications> getNotifications() {
        return Arrays.asList(
                createNotification("ABC","DEF",LocalDateTime.of(2021,12,29,9,30), "Questionnaire","Unresolved"),
                createNotification("XYZ","XYZ",LocalDateTime.of(2021,12,12,10,30), "Questionnaire","Resolved"));
    }

    private Notifications createNotification(String firstName, String lastName, LocalDateTime date, String requestType, String status) {
        Notifications n = new Notifications();
        n.setPatientFirstName(firstName);
        n.setPatientLastName(lastName);
        n.setDate(date);
        n.setRequestType(requestType);
        n.setStatus(status);


        return n;
    }
}
