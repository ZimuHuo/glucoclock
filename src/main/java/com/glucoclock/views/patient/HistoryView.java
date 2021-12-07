package com.glucoclock.views.patient;


import com.glucoclock.data.entity.PersonData;
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
import java.util.Collection;
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

    public HistoryView(){
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


        //testing data
        //Historylist.setItems(x1,x2);
        //Historylist.setItems(x2);

        Historylist.setItems(HistoryDataShown);



        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
    }

    private void setupShownData(){
        //testing data
        ArrayList<PersonData>x=new ArrayList<>();
        PersonData x1=new PersonData();
        x1.setCompleteLogBook(true);
        x1.setDatadate(today);
        x1.setLogBookType("Simple");
        PersonData x2=new PersonData();
        x2.setCompleteLogBook(true);
        x2.setDatadate(today.minusDays(2));
        x2.setLogBookType("Intensive");
        x.add(x1);
        x.add(x2);
        System.out.println(x1.getDatadate());
        System.out.println(x2.getDatadate());
        //testing data
        //compare with database, if there is data occur at that day, add the data in the HistoryDataShown list
        LocalDate date=today;

        for(int day=0;day<30;day++){
            PersonData addData=new PersonData();
            addData.setLogBookType("-");
            addData.setCompleteLogBook(false);
            //set default data- no data, no logbook
            for(PersonData testingdata:x) {
                System.out.println("input: "+testingdata.getDatadate());
                System.out.println("compare w/: "+date.toString());
                if (testingdata.getDatadate().isEqual(today.minusDays(day))) {
                    System.out.println("True");
                    addData.setLogBookType(testingdata.getLogBookType());
                    addData.setCompleteLogBook(testingdata.getBooleanCompleteLogBook());
                }
            }
            addData.setDatadate(date);
            HistoryDataShown.add(addData);
            date=date.minusDays(1);
        }
    }



}
