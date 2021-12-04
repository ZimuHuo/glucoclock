package com.glucoclock.views.patient;


import com.glucoclock.data.entity.PersonData;
import com.glucoclock.data.entity.SamplePerson;
import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Optional;

@PageTitle("View History")
@Route(value = "ViewHistory", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    Grid<PersonData> Historylist=new Grid<>(PersonData.class,false);
    LocalDate today=LocalDate.now();

    public HistoryView(){
        setSizeFull();
        configureHV();

        Historylist.addSelectionListener(selection-> {
            Optional <PersonData>optionalPersonData=selection.getFirstSelectedItem();
            //display date clicked
            Notification.show("Show the data at: "+optionalPersonData.get().getDatadate().toString());
        });

        //layout
        add(
                new H2("View History"),
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

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
        Historylist.setItems(x2,x1);

        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
    }



}
