package com.glucoclock.views.patient;


import com.glucoclock.database.notifications_db.Notifications;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@PageTitle("Notifications")
@Route(value = "patient/notifications")
public class PatientNotificationView extends VerticalLayout {
    private Grid<Notifications> grid;
    private ListDataProvider<Notifications> dataProvider;

    private Grid.Column<Notifications> dateColumn;
    private Grid.Column<Notifications> requestTypeColumn;
    private Grid.Column<Notifications> statusColumn;
    private Grid.Column<Notifications> buttonColumn;

    private MenuBar menu = new MenuBar("PNS");

    private H3 title = new H3("Notifications");
    public PatientNotificationView(){
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
//        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(getNotifications());
        grid.setDataProvider(dataProvider);
    }


    private void addColumnsToGrid() {
        dateColumn = grid.addColumn(Notifications::getDate, "Date").setHeader("Date").setWidth("18%").setFlexGrow(0);
        requestTypeColumn = grid.addColumn(Notifications::getRequestType,"RequestType").setHeader("Request Type").setWidth("18%").setFlexGrow(0);
        statusColumn = grid.addComponentColumn(Notifications::buildStatusBadge).setHeader("Status").setWidth("18%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(Notifications::buildViewButton).setWidth("15%").setFlexGrow(0);
    }



    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();
    }


    private List<Notifications> getNotifications() {
        return Arrays.asList(
                createNotification( LocalDateTime.of(2021,12,29,9,30), "Questionnaire","Unresolved"),
                createNotification(LocalDateTime.of(2021,12,12,10,30), "Questionnaire","Resolved"));
    }

    private Notifications createNotification(LocalDateTime date, String requestType, String status) {
        Notifications n = new Notifications();
        n.setDate(date);
        n.setRequestType(requestType);
        n.setStatus(status);
        return n;
    }
}
