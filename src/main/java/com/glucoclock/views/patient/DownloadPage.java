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
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@PageTitle("Download")
@Route(value = "Download", layout = MainLayout.class)

public class DownloadPage extends VerticalLayout {
    Locale finnishLocale = new Locale("fi", "FI");
    DatePicker PrintStartDate = new DatePicker("Select start date:");
    DatePicker PrintEndDate = new DatePicker("Select end date:");
    Button ExportPlot=new Button("Export Plot");
    Button ExportData=new Button("Export Data");

    Long patientid=1L;

    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;
    private final IntensiveLogBookService IntensivelogData;
    private final LogService Logdata;

    private LocalDate StartDate;
    private LocalDate EndDate;
    private String exportData;

    private MenuBar menu = new MenuBar("PNS");


    public DownloadPage(SimpleLogBookService simplelogData, ComprehensiveLogBookService comprehensivelogData, IntensiveLogBookService intensivelogData, PatientService patientService, LogService logdata) {
        SimplelogData = simplelogData;
        ComprehensivelogData = comprehensivelogData;
        IntensivelogData = intensivelogData;
        Logdata = logdata;

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
        PrintEndDate.addValueChangeListener(e-> PrintStartDate.setMax(e.getValue()));

        //Buttons
        ExportPlot.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        ExportData.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        ExportPlot.addClickListener(click->{
            Notification.show("Download Plot form "+PrintStartDate.getValue()+" to "+PrintEndDate.getValue());
            StartDate=PrintStartDate.getValue();
            EndDate=PrintEndDate.getValue();
        });
        ExportData.addClickListener(click->{
            StartDate=PrintStartDate.getValue();
            EndDate=PrintEndDate.getValue();
            Notification.show("Download Data form "+StartDate+" to "+EndDate);
            exportData= OutputData(SimplelogData,ComprehensivelogData,IntensivelogData);
            System.out.println(exportData);
            //check if the string is empty
            if (exportData.length() == 0) {
                System.out.println("exportData string is empty");
            }
            //export csv
            else {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new File("NewData.csv"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                pw.write(exportData.toString());
                pw.close();
                System.out.println("export CSV");
            }
        });

        //Layout
        HorizontalLayout StartEndDate=new HorizontalLayout(PrintStartDate,PrintEndDate);
        VerticalLayout downLoadpage_layout=new VerticalLayout(new H2("Download"),StartEndDate,ExportPlot,ExportData);
        downLoadpage_layout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(downLoadpage_layout);
    }

    public String OutputData(SimpleLogBookService simple, ComprehensiveLogBookService comprehensive, IntensiveLogBookService intensive){
        String Finaloutput=new String();
        List<Log> PatientData;
        PatientData=Logdata.findLogBooksBetweenDate(StartDate,EndDate,patientid);

        System.out.println(PatientData);
        for (Log eachdata:PatientData){
            if(eachdata.getLogbooktype()==1){
                String simplestring=SimpleOut(eachdata.getDate(),simple);
                //testing
                Finaloutput+=simplestring+"\n";
                //System.out.println(simplestring);
            }

            if(eachdata.getLogbooktype()==2){
                String comprehensivestring=ComprehensiveOut(eachdata.getDate(),comprehensive);
                //testing
                Finaloutput+=comprehensivestring+"\n";
                //System.out.println(comprehensivestring);
            }

            if(eachdata.getLogbooktype()==3){
                String intensivestring=IntensiveOut(eachdata.getDate(),intensive);
                //testing
                Finaloutput+=intensivestring+"\n";
                //System.out.println(intensivestring);
            }

        }


        return Finaloutput;
    }

    public String SimpleOut(LocalDate checkdate, SimpleLogBookService simple){
        String SimpleoutString=new String();


        List<SimpleLogBook> simpledata=simple.findLogByDateAndPatientid(checkdate,patientid);
        for(SimpleLogBook eachdata:simpledata) {
            SimpleoutString += eachdata.toString()+"\n";
        }
        return SimpleoutString;
    }

    public String ComprehensiveOut(LocalDate checkdate, ComprehensiveLogBookService comprehensive){
        String ComprehensiveoutString=new String();
        List<ComprehensiveLogBook> comprehensivedata=comprehensive.findLogByDateAndPatientid(checkdate,patientid);
        for(ComprehensiveLogBook eachdata:comprehensivedata) {
            ComprehensiveoutString += eachdata.toString()+"\n";
        }
        return ComprehensiveoutString;
    }

    public String IntensiveOut(LocalDate checkdate, IntensiveLogBookService intensive){

       String IntensiveoutString=new String();
        List<IntensiveLogBook> intensivedata=intensive.findLogByDateAndPatientid(checkdate,patientid);
        for(IntensiveLogBook eachdata:intensivedata) {
            IntensiveoutString += eachdata.toString()+"\n";
        }
        return IntensiveoutString;
    }

}
