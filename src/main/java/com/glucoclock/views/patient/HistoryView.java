package com.glucoclock.views.patient;


import com.glucoclock.data.entity.PersonData;
import com.glucoclock.data.entity.SamplePerson;
import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public HistoryView(){
        setSizeFull();
        configSearch();
        configureHV();
        setupShownData();
        identifyclick();

        //layout
        add(
                new H2("View History"),
                SearchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

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
        //testing data
        PersonData x1=new PersonData();
        x1.setCompleteLogBook(true);
        x1.setDatadate(today);
        x1.setLogBookType("Simple");
        PersonData x2=new PersonData();
        x2.setCompleteLogBook(false);
        x2.setDatadate(today.plusDays(2));
        x2.setLogBookType("Intensive");

        //add column data of PersonData class
        Historylist.setSizeFull();
        Historylist.addColumn(PersonData::getDatadate).setHeader("Date");
        Historylist.addColumn(PersonData::getCompleteLogBook).setHeader("Complete");
        Historylist.addColumn(PersonData::getLogBookType).setHeader("LogBook Type");


        //testing data
        Historylist.setItems(x1,x2);

        Historylist.setItems(HistoryDataShown);

        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
    }

    private void setupShownData(){
        //ArrayList<PersonData> DatabaseInput
        //compare with database, if there is data occur at that day, add the data in the HistoryDataShown list
        PersonData addData1=new PersonData();
        PersonData addData2=new PersonData();
        LocalDate date=today;
        addData1.setDatadate(today);
        addData1.setLogBookType("-");
        addData1.setCompleteLogBook(false);
        HistoryDataShown.add(addData1);
        addData1.setDatadate(today.minusDays(1));
        addData1.setLogBookType("-");
        addData1.setCompleteLogBook(true);
        HistoryDataShown.add(addData1);


       /* for(int day=0;day<5;day++){
            addData.setDatadate(date);
            HistoryDataShown.add(day,addData);
            if(day==3) addData.setCompleteLogBook(true);
            //date=date.minusDays(1);

        }*/

    }



}
