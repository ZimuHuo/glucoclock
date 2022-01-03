package com.glucoclock.views.doctor;

import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.patient.PatientDownloadView;
import com.glucoclock.views.patient.PersonData;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PageTitle("View Patients Data History")
@Route(value = "doctor/view-patient-history")
public class PatientDataView extends VerticalLayout {
    private String PatientName="(PatientName)";
    private Grid<PersonData> Historylist=new Grid<>(PersonData.class,false);
    private LocalDate today=LocalDate.now();
    private ArrayList<PersonData> HistoryDataShown=new ArrayList<>();
    private DatePicker SelectbyHand= new DatePicker("Select an earlier date");
    private Button ViewData=new Button("View");
    private Button Back =new Button("Back");
    private HorizontalLayout SearchPanel=new HorizontalLayout();
    private final LogService log_db;
    UUID patientid= UUID.fromString("113d2815-54fb-4396-94fb-9a071393c336");
    private MenuBar menu = new MenuBar("DNS");
    private Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private Button downloadBut = new Button(download);
    private Image graph = new Image("images/bgl.png","Blood Glucose Graph");

    public PatientDataView(LogService log_db){
        this.log_db = log_db;
        log_db.bulkcreate();

        setSizeFull();
        configSearch();
        setupShownData();
        configureHV();
        identifyclick();

        graph.setWidth("100%");

        //layout
        download.setSize("50px");
        downloadBut.setHeight("60px");
        downloadBut.setWidth("60px");
        downloadBut.addClickListener(e ->
                downloadBut.getUI().ifPresent(ui ->
                        ui.navigate(PatientDownloadView.class)
                )
        );

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(new H2(PatientName+"'s Data History"),downloadBut);
        hl.setAlignItems(Alignment.BASELINE);

        add(
                hl,
                graph,
                SearchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(menu);

    }

    private void configSearch() {
        SearchPanel.add(SelectbyHand,ViewData);
        SearchPanel.setAlignItems(FlexComponent.Alignment.BASELINE);
        SelectbyHand.addValueChangeListener(e-> Notification.show("the date select is: "+e.getValue().toString()));
    }

    private void identifyclick() {
        Historylist.addSelectionListener(selection-> {
            Optional<PersonData> optionalPersonData=selection.getFirstSelectedItem();
            //display date clicked
            Notification.show("Show the data at: "+optionalPersonData.get().getDatadate().toString());
        });
        Back.addClickListener(back->Back.getUI().ifPresent(ui->ui.navigate(DoctorStartView.class)));

    }

    private void configureHV() {


        //add column data of PersonData class
        Historylist.setHeightFull();
        Historylist.addColumn(PersonData::getDatadate).setHeader("Date (the past 30 days)");
        Historylist.addColumn(PersonData::getCompleteLogBook).setHeader("Complete");
        Historylist.addColumn(PersonData::getLogBookType).setHeader("LogBook Type");



        Historylist.setItems(HistoryDataShown);

        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
        Historylist.setAllRowsVisible(true);

    }

    private void setupShownData(){

        //testing data
        //compare with database, if there is data occur at that day, add the data in the HistoryDataShown list
        LocalDate date=today;

        for(int day=0;day<30;day++){
            List<Log> logbook=log_db.findLogBooksByPatientid(patientid);
            PersonData addData=new PersonData();
            addData.setLogBookType("-");
            addData.setCompleteLogBook(false);
            //set default data- no data, no logbook
            for( Log testingdata:logbook) {
                if (testingdata.getDate().isEqual(today.minusDays(day))) {

                    addData.setLogBookType(testingdata.getStringLogBooktype());
                    addData.setCompleteLogBook(true);
                }
            }
            addData.setDatadate(date);
            HistoryDataShown.add(addData);
            date=date.minusDays(1);
        }
    }

    public void setPatientName(String PatientName){
        this.PatientName=PatientName;
    }



}
