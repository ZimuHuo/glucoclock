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
import com.glucoclock.views.components.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/*
This page is used for plotting the patient glucose level in a chart.
It plots the glucose level by month and store each series in the chart configuration
It allows one user input, which is day. It automatically checks the patient database and display the glucose level within that month
 */
@PageTitle("View Plots")
@Route(value = "/patient/plots-view")
public class PatientPlotView extends Div {
    private H1 space = new H1(" ");
    private H3 title = new H3("Monthly Blood Glucose Report");
    private final UserService userService;
    private final PatientService patientService;
    private final LogService logService;
    private final SimpleLogBookService simpleLogBookService;
    private final ComprehensiveLogBookService comprehensiveLogBookService;
    private final IntensiveLogBookService intensiveLogBookService;
    private DatePicker datePicker;
    private Button plotButton = new Button("View Graph");
    private String userName;
    private UUID patientUid;
    private LocalDate charDate = LocalDate.now();
    private MenuBar menu = new MenuBar("PNS");
    ArrayList<String> log = new ArrayList<>();

    public PatientPlotView(UserService userService, PatientService patientService, LogService logService, SimpleLogBookService simpleLogBookService, ComprehensiveLogBookService comprehensiveLogBookService, IntensiveLogBookService intensiveLogBookService) {
        log.clear();
        this.userService = userService;
        this.patientService = patientService;
        this.logService = logService;
        this.simpleLogBookService = simpleLogBookService;
        this.comprehensiveLogBookService = comprehensiveLogBookService;
        this.intensiveLogBookService = intensiveLogBookService;

        //get patient uid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        userName = authentication.getName();
        User user = this.userService.getRepository().findByUsername(userName); //return user
        patientUid = user.getUid();   //get patient uid

        add(space, createViewEvents(), menu);

    }
/*
create chart view
 */
    private Component createViewEvents() {
        //create chart
        Chart chart = new Chart(ChartType.LINE);
        Configuration conf = chart.getConfiguration();
        XAxis xAxis = new XAxis();
        YAxis yAxis = new YAxis();
        //HorizontalLayout header = createHeader("Monthly Blood Glucose Report", " ");
        conf.getyAxis().setTitle("Blood Glucose (mmol/L)");
        conf.getxAxis().setTitle("Day of the month");
        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setPointPlacement(PointPlacement.ON);
        conf.addPlotOptions(plotOptions);
        this.datePicker = new DatePicker();
        datePicker.setMinWidth("300px");
        datePicker.setLabel("Display data in the selected month");
        datePicker.addValueChangeListener(date -> {
            charDate = date.getValue();
            LocalDate start = charDate.withDayOfMonth(1);
            LocalDate end = charDate.withDayOfMonth(charDate.lengthOfMonth());
            List<Log> patientData = logService.findLogBooksBetweenDate(start, end, patientUid);
            plotButton.setEnabled(false);

            String check =start.getMonth().toString().substring(0,3)+"."+start.getYear();
            if (patientData.isEmpty()) {
                Notification.show("No Data");
            }else if(log.contains(check)){
                Notification.show("The selected month is already plotted",2000, Notification.Position.MIDDLE);
            } else {
                plotButton.setEnabled(true);
                xAxis.setTickInterval(1);
                conf.addxAxis(xAxis);
                conf.getxAxis().setType(AxisType.LINEAR);
            }
        });
//plot the graph and clear the series after plotting it
        plotButton.addClickListener(e -> {
            DataSeries series = getPatientData();
            conf.addSeries(series);
            series.clear();
            plotButton.setEnabled(false);
        });
        // Add it all together
        VerticalLayout viewEvents = new VerticalLayout(chart);
        viewEvents.addClassName("p-l");
        viewEvents.setPadding(false);
        viewEvents.setSpacing(false);
        viewEvents.getElement().getThemeList().add("spacing-l");
        HorizontalLayout selectNPlot = new HorizontalLayout(datePicker, plotButton);
        selectNPlot.setAlignItems(FlexComponent.Alignment.END);
        VerticalLayout vl = new VerticalLayout(title, selectNPlot, viewEvents);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        return vl;
    }

/*
store the patient glucose level in a data series
 */
    public DataSeries getPatientData() {
        DataSeries series = new DataSeries();
        LocalDate start = charDate.withDayOfMonth(1);
        LocalDate end = charDate.withDayOfMonth(charDate.lengthOfMonth());
        series.setName(start.getMonth().toString().substring(0,3)+"."+start.getYear());
        log.add(start.getMonth().toString().substring(0,3)+"."+start.getYear());
        List<Log> patientData = logService.findLogBooksBetweenDate(start, end, patientUid);
        double yval = 0;
        double xval = 0;
        //series.setName(start.getMonth().toString());
        for (Log data : patientData) {

            int type = data.getLogbooktype();

            //type of logbook
            if (type == 1) {
                List<SimpleLogBook> simpleLog = simpleLogBookService.findLogByDateAndPatientuid(data.getDate(), patientUid);
                for (SimpleLogBook simple : simpleLog) {
                    yval = Double.valueOf(simple.getBloodglucose());
                    xval = (double) data.getDate().getDayOfMonth() + (double) 1 / 7 * simple.getTime();
                    series.add(new DataSeriesItem(xval, yval));
                }
            }
            if (type == 2) {
                List<ComprehensiveLogBook> compreLog = comprehensiveLogBookService.findLogByDateAndPatientuid(data.getDate(), patientUid);
                for (ComprehensiveLogBook compre : compreLog) {
                    yval = Double.valueOf(compre.getBloodglucose());
                    xval = (double) data.getDate().getDayOfMonth() + (double) 1 / 7 * compre.getTime();
                    series.add(new DataSeriesItem(xval, yval));
                }
            }
            if (type == 3) {
                List<IntensiveLogBook> intenLog = intensiveLogBookService.findLogByDateAndPatientuid(data.getDate(), patientUid);
                for (IntensiveLogBook inten : intenLog) {
                    yval = Double.valueOf(inten.getBloodglucose());
                    xval = (double) data.getDate().getDayOfMonth() + (double) 1 / 24 * inten.getTime().getHour();
                    series.add(new DataSeriesItem(xval, yval));
                }
            }
        }
        return series;
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
