package com.glucoclock.views.patient;


import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@PageTitle("View History")
@Route(value = "ViewHistory", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    private Grid<PersonData> Historylist=new Grid<>(PersonData.class,false);
    private LocalDate today=LocalDate.now();
    private ArrayList<PersonData> HistoryDataShown=new ArrayList<>();
    private DatePicker SelectbyHand= new DatePicker("Select date");
    private Button ViewData=new Button("View");
    private HorizontalLayout SearchPanel=new HorizontalLayout();
    private final LogService log_db;
    long patientid=1L;
    private MenuBar menu = new MenuBar("PNS");

    public HistoryView(LogService log_db){
        this.log_db = log_db;
        log_db.bulkcreate();

        setSizeFull();
        configSearch();
        setupShownData();
        configureHV();

        identifyclick();

        //layout
        add(
                new H2("View History"),
                SearchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(menu);

    }

    private void configSearch() {
        SearchPanel.add(SelectbyHand,ViewData);
        SearchPanel.setAlignItems(Alignment.BASELINE);
        SelectbyHand.addValueChangeListener(e-> Notification.show("the date select is: "+e.getValue().toString()));
    }

    private void identifyclick() {
        Historylist.addSelectionListener(selection-> {
            Optional <PersonData>optionalPersonData=selection.getFirstSelectedItem();
            //display date clicked
            Notification.show("Show the data at: "+optionalPersonData.get().getDatadate().toString());
        });

    }

    private void configureHV() {


        //add column data of PersonData class
        Historylist.setSizeFull();
        Historylist.addColumn(PersonData::getDatadate).setHeader("Date");
        Historylist.addColumn(PersonData::getCompleteLogBook).setHeader("Complete");
        Historylist.addColumn(PersonData::getLogBookType).setHeader("LogBook Type");



        Historylist.setItems(HistoryDataShown);



        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
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
                //System.out.println("input: "+testingdata.getDatadate());
                //System.out.println("compare w/: "+date.toString());
                if (testingdata.getDate().isEqual(today.minusDays(day))) {

                    addData.setLogBookType(testingdata.getLogbooktype());
                    addData.setCompleteLogBook(true);
                }
            }
            addData.setDatadate(date);
            HistoryDataShown.add(addData);
            date=date.minusDays(1);
        }
    }



}
