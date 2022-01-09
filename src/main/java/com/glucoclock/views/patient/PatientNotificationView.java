package com.glucoclock.views.patient;


import com.glucoclock.database.notifications_db.service.NotificationService;
import com.glucoclock.database.notifications_db.model.Notification;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Notifications")
@Route(value = "patient/notifications")
public class PatientNotificationView extends VerticalLayout {
    private Grid<Notification> grid;
    private ListDataProvider<Notification> dataProvider;

    private Grid.Column<Notification> dateColumn;
    private Grid.Column<Notification> requestTypeColumn;
    private Grid.Column<Notification> statusColumn;
    private Grid.Column<Notification> buttonColumn;

    private MenuBar menu = new MenuBar("PNS");
    private H5 space = new H5(" ");
    private H3 title = new H3("Notifications");

    private final NotificationService notificationService;
    private final UserService userService;

    public PatientNotificationView(NotificationService notificationService, UserService userService){
        this.notificationService = notificationService;
        this.userService = userService;

        VerticalLayout vl = new VerticalLayout();
        setSizeFull();
        createGrid();
        vl.add(title);
        vl.setPadding(true);
        vl.setMargin(true);
        vl.setAlignItems(Alignment.CENTER);
        add(space,vl,grid,menu);
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
        grid.setAllRowsVisible(true);

//        Get the list of notifications
        dataProvider = new ListDataProvider<>(notificationService.getRepository().getNotificationByPatientuid(userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid()));
        grid.setDataProvider(dataProvider);
        grid.setAllRowsVisible(true);
    }


    private void addColumnsToGrid() {
        dateColumn = grid.addColumn(Notification::getDateNoTime, "Date").setHeader("Date").setWidth("25%").setFlexGrow(0);
        requestTypeColumn = grid.addColumn(Notification::getRequestType,"RequestType").setHeader("Request Type").setWidth("30%").setFlexGrow(0);
        statusColumn = grid.addComponentColumn(Notification::buildStatusBadge).setHeader("Status").setWidth("22%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(Notification::buildPatientViewButton).setWidth("23%").setFlexGrow(0);
    }



    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();
    }



}
