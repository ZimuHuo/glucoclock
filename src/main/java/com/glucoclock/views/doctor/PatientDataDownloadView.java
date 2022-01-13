package com.glucoclock.views.doctor;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@PageTitle("Doctor Download Data and Graph")
@Route(value = "doctor/download")

public class PatientDataDownloadView extends VerticalLayout {
    private Locale finnishLocale = new Locale("fi", "FI");
    //use 2 calendars to choose start and end date, when click the export button, export the data between these 2 date
    private DatePicker PrintStartDate = new DatePicker("Select start date:"); //calendar to choose start date
    private DatePicker PrintEndDate = new DatePicker("Select end date:"); //calendar to choose end date
    //Export Buttons, can export plot or export data
    //private Button ExportPlot=new Button("Export Plot");
    private Button ExportData=new Button("Export Data");

    private LocalDate StartDate=LocalDate.now().minusDays(4);
    private LocalDate EndDate=LocalDate.now();
    private String exportData;

    private UUID patientUid;
    //include the databases
    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;
    private final IntensiveLogBookService IntensivelogData;
    private final LogService Logdata;
    private final PatientService patientService;
    private final UserService userService;
    private FileDownloadWrapper buttonWrapper;
    //Menu bar
    private MenuBar menu = new MenuBar("DNS");


    public PatientDataDownloadView(SimpleLogBookService simplelogData, ComprehensiveLogBookService comprehensivelogData, IntensiveLogBookService intensivelogData, PatientService patientService, LogService logdata, PatientService patientService1, UserService userService) {
        // access the data in the databases
        SimplelogData = simplelogData;
        ComprehensivelogData = comprehensivelogData;
        IntensivelogData = intensivelogData;
        Logdata = logdata;
        this.patientService = patientService1;
        this.userService = userService;

        patientUid = (UUID) VaadinSession.getCurrent().getAttribute("PatientID");


        // add menu bar
        add(menu);
        //2 calendar options preset to current time
        PrintStartDate.setLocale(finnishLocale);
        PrintStartDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintStartDate.setHelperText("Format: DD.MM.YYYY");
        PrintEndDate.setLocale(finnishLocale);
        PrintEndDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintEndDate.setHelperText("Format: DD.MM.YYYY");

        //add listener to calendar, make sure start date is before end date
        PrintStartDate.addValueChangeListener(e-> PrintEndDate.setMin(e.getValue()));
        PrintEndDate.addValueChangeListener(e-> EndDate=PrintEndDate.getValue());

//        //export buttons set up
//        ExportPlot.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        ExportData.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//
//        //Export Plot
//        ExportPlot.addClickListener(click->{
//            Notification.show("Download Plot form "+PrintStartDate.getValue()+" to "+PrintEndDate.getValue());
//        });

        // Export Data
        //set up the button wrapper function
        //when click the button, the data between the 2 selected date will be export in csv file
        this.buttonWrapper = new FileDownloadWrapper(
                //csv file set up
                new StreamResource("Datafile"+ ".csv", () -> {
                    //export the data between the 2 date selected
                    //exportData is the returned string from the OutputData method
                    exportData= OutputData();
                    return new ByteArrayInputStream(exportData.getBytes(StandardCharsets.UTF_8));
                })
        );

        //add button wrapper to the Export Data button
        buttonWrapper.wrapComponent(ExportData);
        //each time the export data button is clicked, the startdate and enddate will be renewed
        ExportData.addFocusListener(event -> {
            StartDate=PrintStartDate.getValue();
            EndDate=PrintEndDate.getValue();
        });


        //Layout
        HorizontalLayout StartEndDate=new HorizontalLayout(PrintStartDate,PrintEndDate);
        VerticalLayout downLoadpage_layout=new VerticalLayout(new H3("Download "+patientService.getRepository().getPatientByUid(patientUid).getFirstName()+" "+patientService.getRepository().getPatientByUid(patientUid).getLastName()+"'s Logbook Data"),StartEndDate, buttonWrapper);
        downLoadpage_layout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(downLoadpage_layout);
    }

    public String OutputData(){
        //set up the returned string, this is the first 2 lines of the csv file
        String Finaloutput=
                "Start date"+","+StartDate.toString()+","+"End date"+","+EndDate.toString()+"\n" +
                        "Patient name"+","+patientService.searchPatientname(patientUid)+"\n"+
                        "LogBook Type" +
                        "," + "Date" +
                        "," + "Time" +
                        "," + "Blood glucose" +
                        "," + "Carb Intake" +
                        "," + "Insulindose"+
                        "," + "Carb bolus" +
                        "," + "High BS Bolus" +
                        "," + "Basal Rate" +
                        "," + "Ketones"+"\n"
                ;

        List<Log> PatientData;//create a list store the data fit requirement (from logdata database)
        //find the data of this patient (patient id), between StartDate and EndDate, store them in the PatientData list
        // the list will contain logbook type and date and patient id
        PatientData=Logdata.findLogBooksBetweenDate(StartDate,EndDate,patientUid);

        // check in corresponding logbook database(find the logbook type from the list) for each data in the PatientData list
        for (Log eachdata:PatientData){
            //if this data is Simplelogbook
            if(eachdata.getLogbooktype()==1){
                //use SimpleOut method to get the string which contain all the data at that date
                String simplestring=SimpleOut(eachdata.getDate());
                //add the string to the returned string
                Finaloutput+=simplestring+"\n";

            }

            //if this data is Comprehensivelogbook
            if(eachdata.getLogbooktype()==2){
                //use ComprehensiveOut method to get the string which contain all the data at that date
                String comprehensivestring=ComprehensiveOut(eachdata.getDate());
                //add the string to the returned string
                Finaloutput+=comprehensivestring+"\n";
            }

            //if this data is Intensivelogbook
            if(eachdata.getLogbooktype()==3){
                //use IntensiveOut method to get the string which contain all the data at that date
                String intensivestring=IntensiveOut(eachdata.getDate());
                //add the string to the returned string
                Finaloutput+=intensivestring+"\n";
            }
        }

        return Finaloutput;
    }

    public String SimpleOut(LocalDate checkdate){
        String SimpleoutString=new String();//set up returned string
        //find the data for this patient at checkdate, and stored the data in the simpledata list
        List<SimpleLogBook> simpledata=SimplelogData.findLogByDateAndPatientuid(checkdate,patientUid);
        for(SimpleLogBook eachdata:simpledata) {
            //get each data in string form, and added to the returned string
            SimpleoutString += eachdata.toString()+"\n";
        }
        return SimpleoutString;
    }

    public String ComprehensiveOut(LocalDate checkdate){
        //set up the returned string
        String ComprehensiveoutString=new String();
        //find the data for this patient at checkdate, and stored the data in the comprehensivedata list
        List<ComprehensiveLogBook> comprehensivedata=ComprehensivelogData.findLogByDateAndPatientuid(checkdate,patientUid);
        for(ComprehensiveLogBook eachdata:comprehensivedata) {
            //get each data in string form, and added to the returned string
            ComprehensiveoutString += eachdata.toString()+"\n";
        }
        return ComprehensiveoutString;
    }

    public String IntensiveOut(LocalDate checkdate){
        //set up the returned string
        String IntensiveoutString=new String();
        //find the data for this patient at checkdate, and stored the data in the intensivedata list
        List<IntensiveLogBook> intensivedata=IntensivelogData.findLogByDateAndPatientuid(checkdate,patientUid);
        for(IntensiveLogBook eachdata:intensivedata) {
            //get each data in string form, and added to the returned string
            IntensiveoutString += eachdata.toString()+"\n";
        }
        return IntensiveoutString;
    }

}