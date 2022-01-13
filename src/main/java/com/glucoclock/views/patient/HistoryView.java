package com.glucoclock.views.patient;


import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PageTitle("View Data History")
@Route(value = "patient/view-history")
public class HistoryView extends VerticalLayout {
    //patient id
    private UUID patientUid;

    //UI components
    private MenuBar menu = new MenuBar("PNS");
    private H3 space = new H3("   ");
    private H3 title = new H3("Logbook Data History");
    private Icon download = new Icon(VaadinIcon.DOWNLOAD);
    private HorizontalLayout SearchPanel=new HorizontalLayout();
    //DataPicker
    private DatePicker SelectbyHand= new DatePicker("Select an earlier date");
    //Button
    private Button downloadBut = new Button(download);
    private Button ViewData=new Button("View");

    //Grid
    private Grid<PersonData> Historylist=new Grid<>(PersonData.class,false);
    private ArrayList<PersonData> HistoryDataShown=new ArrayList<>();
    private LocalDate today=LocalDate.now();
    private LocalDate checkDate;

    //Database
    private final LogService log_db;
    private final UserService userService;
    public HistoryView(LogService log_db, UserService userService){
        //database
        this.log_db = log_db;
        this.userService = userService;

        //get patient id using authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        User user= this.userService.getRepository().findByUsername(authentication.getName()); //return user
        patientUid=user.getUid();   //get patient uid

        configSearch();     //configuration of search panel->set search panel functionality
        setupShownData();   //put data into Arraylist HistoryDataShown
        configureHV();      //configuration of the grid
        identifyclick();    //identify click grid



        HorizontalLayout hl = new HorizontalLayout();
        download.setSize("50px");
        downloadBut.setHeight("60px");
        downloadBut.setWidth("60px");
        downloadBut.addClickListener(e ->
                downloadBut.getUI().ifPresent(ui ->
                        ui.navigate(PatientDownloadView.class)
                )
        );
        hl.add(title,downloadBut);
        hl.setAlignItems(Alignment.BASELINE);

        //Final layout
        add(    space,
                hl,
                SearchPanel,
                Historylist);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(menu);
    }


    private void configSearch() {
        SelectbyHand.setMax(LocalDate.now());
        SearchPanel.add(SelectbyHand,ViewData);
        SearchPanel.setAlignItems(Alignment.BASELINE);
        //get date
        SelectbyHand.addValueChangeListener(e-> checkDate=e.getValue());
        //save in session
        ViewData.addClickListener(view->{
            Log logBook=log_db.findLogBookbyDate(checkDate,patientUid);
            String logbookType=null; //if no logbook entered at that day string will be null
            //Put date in session
            VaadinSession.getCurrent().setAttribute("CheckDate",checkDate);
            //not null logbook
            if(logBook!=null) {
                logbookType=logBook.getStringLogBooktype();
                //Put logbood type in session
                VaadinSession.getCurrent().setAttribute("LogbookType", logbookType);
                ViewData.getUI().ifPresent((ui -> ui.navigate(LogbookView.class)));
            }
            else Notification.show("No Data").addThemeVariants(NotificationVariant.LUMO_ERROR);



        });

    }

    private void setupShownData(){
        //compare with database, if there is data occur at that day, add the data in the HistoryDataShown list
        LocalDate date=today;
        List<Log> logbook=log_db.findLogBooksByPatientid(patientUid);

        //only show the past 30 days
        for(int day=0;day<30;day++){
            //for each date
            //set default data- no data, no logbook
            PersonData addData=new PersonData();
            addData.setLogBookType("-");
            addData.setCompleteLogBook(false);
            addData.setDatadate(date);
            //look through logbook
            for( Log testingdata:logbook) {
                //compare exist logbook date with this "day"
                if (testingdata.getDate().isEqual(today.minusDays(day))) {
                    //exist data at this date, reset PersonDate addData
                    addData.setLogBookType(testingdata.getStringLogBooktype());
                    addData.setCompleteLogBook(true);
                }
            }
            //add to Arraylist HistoryDataShown
            HistoryDataShown.add(addData);
            //iteration, start a new loop
            date=date.minusDays(1);
        }
    }

    private void configureHV() {
        //add column data of PersonData class
        Historylist.setHeightFull();
        //set the title row
        Historylist.addColumn(PersonData::getDatadate).setHeader("Date (the past 30 days)");
        Historylist.addColumn(PersonData::getCompleteLogBook).setHeader("Complete");
        Historylist.addColumn(PersonData::getLogBookType).setHeader("LogBook Type");
        //add Data
        Historylist.setItems(HistoryDataShown);
        //settings
        Historylist.getColumns().forEach(col->col.setAutoWidth(true));
        Historylist.setAllRowsVisible(true);

    }

    private void identifyclick() {
        Historylist.addSelectionListener(selection-> {
            Optional <PersonData>optionalPersonData=selection.getFirstSelectedItem();
            //Put date in session
            VaadinSession.getCurrent().setAttribute("CheckDate",optionalPersonData.get().getDatadate());
            //Put logbood type in session
            VaadinSession.getCurrent().setAttribute("LogbookType",optionalPersonData.get().getLogBookType());
            //when clicked, navigate to LogbookView page
            Historylist.getUI().ifPresent(ui->ui.navigate(LogbookView.class));
            //display date clicked
            Notification.show("Show the data at: "+optionalPersonData.get().getDatadate().toString());
        });
    }



}
