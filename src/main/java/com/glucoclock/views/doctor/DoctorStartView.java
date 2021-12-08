package com.glucoclock.views.doctor;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Doctor Start View")
@Route(value = "DoctorStartView", layout = MainLayout.class)
public class DoctorStartView extends VerticalLayout {
    private Grid<PatientInfo> grid;
    private ListDataProvider<PatientInfo> dataProvider;

    private Grid.Column<PatientInfo> firstNameColumn;
    private Grid.Column<PatientInfo> lastNameColumn;
    private Grid.Column<PatientInfo> emailColumn;
    private Grid.Column<PatientInfo> buttonColumn;

    private MenuBar menu = new MenuBar("DStart");

    private HorizontalLayout hl = new HorizontalLayout();
    private H3 title = new H3("My Patients");
    private Icon add = new Icon(VaadinIcon.PLUS_CIRCLE);




    public DoctorStartView() {
        add.setSize("55px");
        Button adder = new Button(add);
        adder.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        hl.add(title,adder);
        hl.setHeight("20%");
        hl.setVerticalComponentAlignment(Alignment.BASELINE,title,adder);
        hl.setSpacing(false);
        setSizeFull();
        createGrid();
        add(hl,grid);
        setAlignItems(Alignment.CENTER);
        add(menu);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(getPatients());
        grid.setDataProvider(dataProvider);
    }

    private Button buildDeleteButton( ) {
        Button button = new Button("123");
        return button;
    }

    private void addColumnsToGrid() {
        firstNameColumn = grid.addColumn(PatientInfo::getFirstName, "FirstName").setHeader("First Name").setWidth("20%").setFlexGrow(0);
        lastNameColumn = grid.addColumn(PatientInfo::getLastName, "LastName").setHeader("Last Name").setWidth("20%").setFlexGrow(0);
        emailColumn = grid.addColumn(PatientInfo::getEmail, "Email").setHeader("Email").setWidth("20%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(PatientInfo::buildDeleteButton).setWidth("20%").setFlexGrow(0);
    }



    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField firstNameFilter = new TextField();
        firstNameFilter.setPlaceholder("Filter");
        firstNameFilter.setClearButtonVisible(true);
        firstNameFilter.setWidth("100%");
        firstNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        firstNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getFirstName(), firstNameFilter.getValue())));
        filterRow.getCell(firstNameColumn).setComponent(firstNameFilter);

        TextField lastNameFilter = new TextField();
        lastNameFilter.setPlaceholder("Filter");
        lastNameFilter.setClearButtonVisible(true);
        lastNameFilter.setWidth("100%");
        lastNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        lastNameFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getLastName(), lastNameFilter.getValue())));
        filterRow.getCell(lastNameColumn).setComponent(lastNameFilter);

        TextField emailFilter = new TextField();
        emailFilter.setPlaceholder("Filter");
        emailFilter.setClearButtonVisible(true);
        emailFilter.setWidth("100%");
        emailFilter.setValueChangeMode(ValueChangeMode.EAGER);
        emailFilter.addValueChangeListener(event -> dataProvider
                .addFilter(patientInfo -> StringUtils.containsIgnoreCase(patientInfo.getEmail(), emailFilter.getValue())));
        filterRow.getCell(emailColumn).setComponent(emailFilter);

    }


    private List<PatientInfo> getPatients() {
        return Arrays.asList(
                createPatient("ABC","DEF","abcdef@ic.ac.uk"),
                createPatient("Imperial","College","ic@gmail.com"));
    }

    private PatientInfo createPatient(String firstName, String lastName, String email) {
        PatientInfo p = new PatientInfo();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setEmail(email);
        return p;
    }
}
