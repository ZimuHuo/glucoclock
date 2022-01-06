package com.glucoclock.views.patient;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@PageTitle("Start Page")
@Route(value = "/patient/start-page")
@RolesAllowed("PATIENT")
public class PatientStartView extends VerticalLayout{
    private ComboBox<String> LBtybe;
    private DatePicker uploaddatePicker;
    private DatePicker chartdatePicker;
    private Button plotButton;
    private Icon update;
    private Button updateButton;
    private MenuBar menu = new MenuBar("PStart");
    private H2 title = new H2("Upload Logbook Entry");
    private LocalDate selectDate = LocalDate.now();
    private int logbookType=0;
    private String userName;
    private UUID patientUid;
    private LocalDate charDate = LocalDate.now();

    private final UserService userService;
    private final PatientService patientService;
    private final LogService logService;
    private final SimpleLogBookService simpleLogBookService;
    private final ComprehensiveLogBookService comprehensiveLogBookService;
    private final IntensiveLogBookService intensiveLogBookService;

    //Chart Function
    private ArrayList<String> chartTime=new ArrayList<>();
    private ArrayList<Integer> chartBloodglucose=new ArrayList<>();
    private Log PatientData;//create a list store the data fit requirement (from logdata database)



    public PatientStartView(UserService userService, PatientService patientService, LogService logService, SimpleLogBookService simpleLogBookService, ComprehensiveLogBookService comprehensiveLogBookService, IntensiveLogBookService intensiveLogBookService){
        //database
        this.userService = userService;
        this.patientService = patientService;
        this.logService = logService;
        this.simpleLogBookService = simpleLogBookService;
        this.comprehensiveLogBookService = comprehensiveLogBookService;
        this.intensiveLogBookService = intensiveLogBookService;

        //get patient uid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        userName=authentication.getName();
        User user= this.userService.getRepository().findByUsername(userName); //return user
        patientUid=user.getUid();   //get patient uid

        add(menu);

        //create testing database


        newLogBook();//add new logbook function

        setHorizontalComponentAlignment(Alignment.CENTER,title,LBtybe,uploaddatePicker,updateButton);

        add(createViewEvents(),title,LBtybe,uploaddatePicker,updateButton);

    }

    private void newLogBook() {
        //Upload Logbook Function
        LBtybe = new ComboBox<>();
        LBtybe.setLabel("Logbook\nType");
        LBtybe.setItems("Simple","Comprehensive","Intensive");
        LBtybe.addCustomValueSetListener(event -> LBtybe.setValue(event.getDetail()));

        uploaddatePicker = new DatePicker("Date");
        Locale finnishLocale = new Locale("fi", "FI");
        uploaddatePicker.setLocale(finnishLocale);
        uploaddatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));

        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateButton = new Button(update);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidth("120px");
        updateButton.setHeight("120px");

        //select date
        uploaddatePicker.addValueChangeListener(date-> {
            selectDate=date.getValue();
        });


        //select logbook type
        LBtybe.addValueChangeListener(event -> {
            //VaadinSession.getCurrent().setAttribute( "date",selectDate);
            if (event.getValue() == "Simple") logbookType=1;//simple logbook->1
            else if (event.getValue() == "Comprehensive") logbookType=2;//comprehensive logbook->2
            else if (event.getValue() == "Intensive") logbookType=3;//intensive logbook->3
            //System.out.println(event.getValue());;
        });

        //upload data
        updateButton.addClickListener(e ->{
                    VaadinSession.getCurrent().setAttribute( "date",selectDate);
                    if(logbookType==1) updateButton.getUI().ifPresent(ui -> ui.navigate(SimpleLogbookView.class));
                    else if(logbookType==2) updateButton.getUI().ifPresent(ui -> ui.navigate(ComprehensiveLogbookView.class));
                    else if(logbookType==3) updateButton.getUI().ifPresent(ui -> ui.navigate(IntensiveLogbookView.class));
                    else if (logbookType==0) Notification.show("Please choose logbook type!");
                }
        );
    }

    private Component createViewEvents() {
// Header
        //create chart
        DataSeries series = new DataSeries();

        Chart chart = new Chart(ChartType.LINE);
        Configuration conf = chart.getConfiguration();
        XAxis xAxis = new XAxis();
        YAxis yAxis = new YAxis();
        HorizontalLayout header = createHeader("Past Blood Glucose Level", "units");
        plotButton = new Button("Plot");
        chartdatePicker=new DatePicker("view the plot at: ");
        chartdatePicker.setValue(LocalDate.now());
        header.add(chartdatePicker,plotButton);


        conf.getyAxis().setTitle("Values");
        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setPointPlacement(PointPlacement.ON);
        conf.addPlotOptions(plotOptions);

        chartdatePicker.addValueChangeListener(date->{
            charDate=date.getValue();
            List<Log> patientData= logService.findLogBooksByPatientid(patientUid);
            double yval = 0;
            double xval = 0;
            System.out.println("no data"+patientData.toString());
            if(patientData.isEmpty()){
                Notification.show("No Data");
            }
            else {
                for(Log data: patientData){
                    int type = data.getLogbooktype();
                    String time = String.valueOf(data.getTime());
                    if(type==1){
                        SimpleLogBook simpleLogBook = simpleLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,data.getTime(),data.getDate());
                        yval = Double.valueOf(simpleLogBook.getBloodglucose());
                        xval = (double)data.getDate().getDayOfMonth()+(double)1/6*data.getTime();

                    }
                    if(type ==2){
                        ComprehensiveLogBook comprehensiveLogBook = comprehensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,data.getTime(),data.getDate());
                        yval = Double.valueOf(comprehensiveLogBook.getBloodglucose());
                        xval = (double)data.getDate().getDayOfMonth()+(double)1/6*data.getTime();
                    }
                    if (type ==3){
                        LocalTime timefinder = LocalTime.of(data.getTime(), 00, 00);
                        IntensiveLogBook intensiveLogBook = intensiveLogBookService.getRepository().findByPatientuidAndTimeAndDate(patientUid,timefinder,data.getDate());
                        yval = Double.valueOf(intensiveLogBook.getBloodglucose());
                        xval = (double)data.getDate().getDayOfMonth()+(double)1/24*data.getTime();
                    }

                    series.add(new DataSeriesItem(xval, yval));
                }
            }
        });

        xAxis.setTickInterval(1);
        conf.addxAxis(xAxis);
        conf.getxAxis().setType(AxisType.LINEAR);

        plotButton.addClickListener(e ->{
            conf.addSeries(series);
        });




        // Add it all together
        VerticalLayout viewEvents = new VerticalLayout(header, chart);
        viewEvents.addClassName("p-l");
        viewEvents.setPadding(false);
        viewEvents.setSpacing(false);
        viewEvents.getElement().getThemeList().add("spacing-l");
        return viewEvents;
    }
    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private HorizontalLayout createHeader(String title, String subtitle) {
        H2 h2 = new H2(title);
        h2.addClassNames("text-xl", "m-0");

        Span span = new Span(subtitle);
        span.addClassNames("text-secondary", "text-xs");

        VerticalLayout column = new VerticalLayout(h2, span);
        column.setPadding(false);
        column.setSpacing(false);

        HorizontalLayout header = new HorizontalLayout(column);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setSpacing(false);
        header.setWidthFull();
        return header;
    }


}