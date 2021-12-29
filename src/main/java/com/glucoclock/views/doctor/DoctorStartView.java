package com.glucoclock.views.doctor;

import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

@PageTitle("My Patients")
@Route(value = "doctor/my-patients")
public class DoctorStartView extends VerticalLayout {
    private Grid<PatientInfo> grid;
    private ListDataProvider<PatientInfo> dataProvider;

    private Grid.Column<PatientInfo> firstNameColumn;
    private Grid.Column<PatientInfo> lastNameColumn;
    private Grid.Column<PatientInfo> emailColumn;
    private Grid.Column<PatientInfo> logbookColumn;
    private Grid.Column<PatientInfo> buttonColumn;

    private MenuBar menu = new MenuBar("DStart");

    private HorizontalLayout hl = new HorizontalLayout();
    private H3 title = new H3("My Patients");
    private Icon add = new Icon(VaadinIcon.PLUS_CIRCLE);
    private Button addBut = new Button(add);




    public DoctorStartView() {
        add.setSize("50px");
        addBut.setWidth("55px");
        addBut.setHeight("55px");
        //addBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        hl.add(title,addBut);
        hl.setHeight("20%");
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.BASELINE,title,addBut);

        addBut.addClickListener(e->
                addBut.getUI().ifPresent(ui ->
                ui.navigate(AddPatientView.class))
        );
        //hl.setSpacing(false);
        setSizeFull();
        createGrid();
        VerticalLayout vl = new VerticalLayout();
        vl.add(hl);
        vl.setPadding(true);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
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
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(getPatients());
        grid.setDataProvider(dataProvider);
    }


    private void addColumnsToGrid() {
        firstNameColumn = grid.addColumn(PatientInfo::getFirstName, "FirstName").setHeader("First Name").setWidth("17%").setFlexGrow(0);
        lastNameColumn = grid.addColumn(PatientInfo::getLastName, "LastName").setHeader("Last Name").setWidth("17%").setFlexGrow(0);
        emailColumn = grid.addColumn(PatientInfo::getEmail, "Email").setHeader("Email").setWidth("28%").setFlexGrow(0);
        logbookColumn = grid.addComponentColumn(PatientInfo::buildLogbookMenu).setHeader("Suggested Logbook Type").setWidth("18%").setFlexGrow(0);
        buttonColumn = grid.addComponentColumn(PatientInfo::buildViewButton).setWidth("14%").setFlexGrow(0);
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
