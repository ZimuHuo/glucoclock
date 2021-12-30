package com.glucoclock.views.researcher;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.log_db.model.Log;
import com.glucoclock.database.log_db.service.LogService;
import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@PageTitle("Search for and Download Anonymised Data")
@Route(value = "researcher/data-searcher-and-download")
public class ResearcherStart extends VerticalLayout {
    double F_AgeMin;
    double F_AgeMax;
    LocalDate F_AgeMin_date;
    LocalDate F_AgeMax_date;
    String F_Gender;
    String F_Insulin;
    Boolean F_RapidInsulin = false;
    Boolean F_ShortInsulin = false;
    Boolean F_InterInsulin = false;
    Boolean F_LongInsulin = false;
    String F_Diabetes;
    String F_Result;

    FileDownloadWrapper buttonWrapper;

    private final PatientService patientService;
    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;
    private final IntensiveLogBookService IntensivelogData;
    private final LogService Logdata;

    private H2 title = new H2("Download Anonymised Patient Data");
    private MenuBar menu = new MenuBar("RStart");

    public ResearcherStart(PatientService patientService, SimpleLogBookService simplelogData, ComprehensiveLogBookService comprehensivelogData, IntensiveLogBookService intensivelogData, LogService logdata) {
        //Databases
        this.patientService = patientService;
        patientService.bulkcreate();
        simplelogData.bulkcreate();
        comprehensivelogData.bulkcreate();
        intensivelogData.bulkcreate();
        logdata.bulkcreate();
        SimplelogData = simplelogData;
        ComprehensivelogData = comprehensivelogData;
        IntensivelogData = intensivelogData;
        Logdata = logdata;
        add(menu);
        //Buttons and blanks
        //Gender filter
        ComboBox<String> Filter_Gender = new ComboBox<>("Gender");
        Filter_Gender.setAllowCustomValue(true);
        Filter_Gender.setItems("Female", "Male", "Any");
        Filter_Gender.addValueChangeListener(gender -> {
            Notification.show(gender.getValue());
            F_Gender = gender.getValue();
        });

        //Age filter
        NumberField Filter_LowLim = new NumberField("Age from");
        NumberField Filter_HighLIm = new NumberField(" to ");
        Filter_LowLim.addValueChangeListener(e -> {
            //change the input age number in to a date, by minus years
            F_AgeMin = e.getValue();
            F_AgeMin_date = LocalDate.now().minusYears(e.getValue().longValue());
            Filter_HighLIm.setMin(F_AgeMin);
            Notification.show("Age Min: " + F_AgeMin);
        });
        Filter_HighLIm.addValueChangeListener(e -> {
            //change the input age number in to a date, by minus years
            F_AgeMax = e.getValue();
            F_AgeMax_date = LocalDate.now().minusYears(e.getValue().longValue());
            Filter_LowLim.setMax(F_AgeMax);
            Notification.show("Age Max: " + F_AgeMax);
        });
        //Insulin filter
        ComboBox<String> Filter_Insulin = new ComboBox<>("Insulin type ");
        Filter_Insulin.setAllowCustomValue(true);
        Filter_Insulin.setItems("Rapid acting insulin", "Short acting insulin", "Intermediate acting insulin", "Long acting insulin", "Any");
        Filter_Insulin.addValueChangeListener(insulin -> {
            Notification.show(insulin.getValue());
            F_Insulin = insulin.getValue();
            //write the insulin type the researcher wanted in the Boolean variables
//            if (F_Insulin.equals("Rapid acting insulin")) {
//                F_RapidInsulin = true;
//                F_ShortInsulin = false;
//                F_InterInsulin = false;
//                F_LongInsulin = false;
//            }
//            if (F_Insulin.equals("Short acting insulin")) {
//                F_RapidInsulin = false;
//                F_ShortInsulin = true;
//                F_InterInsulin = false;
//                F_LongInsulin = false;
//            }
//            if (F_Insulin.equals("Intermediate acting insulin")) {
//                F_RapidInsulin = false;
//                F_ShortInsulin = false;
//                F_InterInsulin = true;
//                F_LongInsulin = false;
//            }
//            if (F_Insulin.equals("Long acting insulin")) {
//                F_RapidInsulin = false;
//                F_ShortInsulin = false;
//                F_InterInsulin = false;
//                F_LongInsulin = true;
//            }
//            if (F_Insulin.equals("Any")) {
//                F_RapidInsulin = true;
//                F_ShortInsulin = true;
//                F_InterInsulin = true;
//                F_LongInsulin = true;
//            }

        });
        //Diabetes filter
        ComboBox<String> Filter_Diabetes = new ComboBox<>("Diabetes type ");
        Filter_Diabetes.setAllowCustomValue(true);
        Filter_Diabetes.setItems("Type I", "Type II", "Any");
        Filter_Diabetes.addValueChangeListener(diabetes -> {
            Notification.show(diabetes.getValue());
            F_Diabetes = diabetes.getValue();
        });
        //Button apply filter
        Button Filter_apply = new Button("Apply Filter");
        Filter_apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //Download button
        Icon Filter_download = new Icon(VaadinIcon.DOWNLOAD);
        Filter_download.setSize("80px");
        Button download = new Button(Filter_download);
        download.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        download.setHeight("100px");

        //Click listener of the Filter_apply
        Filter_apply.addClickListener(click->{
            String notification=CheckOutputNum();
            Notification.show(notification);
        });

        //Button wrapper
        // Download Data
        //set up the button wrapper function
        //when click the button, the data between the 2 selected date will be export in csv file
        this.buttonWrapper = new FileDownloadWrapper(
                //csv file set up
                new StreamResource("Datafile"+ ".csv", () -> {
                    //export the data between the 2 date selected
                    //exportData is the returned string from the OutputData method
                    F_Result= OutputData();
                    System.out.println(F_Result);
                    return new ByteArrayInputStream(F_Result.getBytes(StandardCharsets.UTF_8));
                })
        );
        //add button wrapper to the Download data
        this.buttonWrapper.wrapComponent(download);

        //Layout
        FormLayout ResearchFilter = new FormLayout();
        ResearchFilter.add(Filter_Gender, Filter_LowLim, Filter_HighLIm);
        ResearchFilter.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2),
                // Use three columns, if the layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 3)
        );
        ResearchFilter.add(Filter_Insulin, Filter_Diabetes, Filter_apply);

        VerticalLayout FinalLayout = new VerticalLayout(title, ResearchFilter, buttonWrapper);
        FinalLayout.setAlignItems(FlexComponent.Alignment.CENTER);


        add(FinalLayout);

    }

    public String CheckOutputNum(){
        String returnString="There are ";
        List<Patient> patientList;
        patientList = patientService.researcherSearch(F_AgeMax_date, F_AgeMin_date, F_Gender, F_Diabetes, F_Insulin);
        returnString+=patientList.size()+" Patients data fit your requirement.";
        return returnString;
    }

    public String OutputData() {
        int patientNumber = 1;
        List<Patient> patientList;
        String Finaloutput =
                "Minimum age:" + "," + F_AgeMin + "," + "Maximum age:" + "," + F_AgeMax + "," + "Gender: " + F_Gender + "," + "Insulin type: " + F_Insulin + "," + "Diabetes Type:" + F_Diabetes + "\n\n"+
                        "Patient Number"+
                        ","+"LogBook Type" +
                        "," + "Date" +
                        "," + "Time" +
                        "," + "Blood glucose" +
                        "," + "Carb Intake" +
                        "," + "Insulindose"+
                        "," + "Carb bolus" +
                        "," + "High BS Bolus" +
                        "," + "Basal Rate" +
                        "," + "Ketones"+"\n";

        System.out.println(F_AgeMin_date);
        System.out.println(F_AgeMax_date);
        patientList = patientService.researcherSearch(F_AgeMax_date, F_AgeMin_date, F_Gender, F_Diabetes, F_Insulin);
        //System.out.println(patientList);

        for (Patient thispatient : patientList) {
            Long patientid;
            patientid = thispatient.getPatientid();

            //seperate each patient data
            Finaloutput = Finaloutput + "Patient" + patientNumber + "\n";
            List<Log> PatientData;//create a list store the data fit requirement (from logdata database)
            //find the data of this patient (patient id), store them in the PatientData list
            // the list will contain logbook type and date and patient id
            PatientData = Logdata.findLogBooksByPatientid(patientid);

            // check in corresponding logbook database(find the logbook type from the list) for each data in the PatientData list
            for (Log eachdata : PatientData) {
                //if this data is Simplelogbook
                if (eachdata.getLogbooktype() == 1) {
                    //use SimpleOut method to get the string which contain all the data at that date
                    String simplestring = SimpleOut(eachdata.getDate(), patientid);
                    //add the string to the returned string
                    Finaloutput += simplestring + "\n";

                }
                //if this data is Comprehensivelogbook
                if (eachdata.getLogbooktype() == 2) {
                    //use ComprehensiveOut method to get the string which contain all the data at that date
                    String comprehensivestring = ComprehensiveOut(eachdata.getDate(), patientid);
                    //add the string to the returned string
                    Finaloutput += comprehensivestring + "\n";
                }

                //if this data is Intensivelogbook
                if (eachdata.getLogbooktype() == 3) {
                    //use IntensiveOut method to get the string which contain all the data at that date
                    String intensivestring = IntensiveOut(eachdata.getDate(), patientid);
                    //add the string to the returned string
                    Finaloutput += intensivestring + "\n";
                }

            }
                System.out.println(patientid);

            patientNumber+=1;
            }

            return Finaloutput;
    }
    public String SimpleOut (LocalDate checkdate, Long patientid){
        String SimpleoutString = new String();//set up returned string
        //find the data for this patient at checkdate, and stored the data in the simpledata list
        List<SimpleLogBook> simpledata = SimplelogData.findLogByDateAndPatientid(checkdate, patientid);
        for (SimpleLogBook eachdata : simpledata) {
            //get each data in string form, and added to the returned string
            SimpleoutString += eachdata.toString() + "\n";
        }
        return SimpleoutString;
    }

    public String ComprehensiveOut (LocalDate checkdate, Long patientid){
        //set up the returned string
        String ComprehensiveoutString = new String();
        //find the data for this patient at checkdate, and stored the data in the comprehensivedata list
        List<ComprehensiveLogBook> comprehensivedata = ComprehensivelogData.findLogByDateAndPatientid(checkdate, patientid);
        for (ComprehensiveLogBook eachdata : comprehensivedata) {
            //get each data in string form, and added to the returned string
            ComprehensiveoutString += eachdata.toString() + "\n";
        }
        return ComprehensiveoutString;
    }

    public String IntensiveOut (LocalDate checkdate, Long patientid){
        //set up the returned string
        String IntensiveoutString = new String();
        //find the data for this patient at checkdate, and stored the data in the intensivedata list
        List<IntensiveLogBook> intensivedata = IntensivelogData.findLogByDateAndPatientid(checkdate, patientid);
        for (IntensiveLogBook eachdata : intensivedata) {
            //get each data in string form, and added to the returned string
            IntensiveoutString += eachdata.toString() + "\n";
        }
        return IntensiveoutString;
    }
}

