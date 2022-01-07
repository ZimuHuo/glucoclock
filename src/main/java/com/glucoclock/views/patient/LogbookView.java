package com.glucoclock.views.patient;


import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@PageTitle("My Logbook")
@Route(value = "patient/logbook")
@RolesAllowed("ADMIN")
public class LogbookView extends VerticalLayout {
    //this patient id
    private UUID patientUid;         //set to 1 for testing
    private LocalDate SelectDate=LocalDate.now();   //will get from previous page
    private String LogbookType="Simple";

    //UI components
    private MenuBar menu = new MenuBar("PNS");
    private Button Back=new Button("Back");

    //Database
    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;
    private final IntensiveLogBookService IntensivelogData;
    private final UserService userService;




    public LogbookView(SimpleLogBookService simplelogData, ComprehensiveLogBookService comprehensivelogData, IntensiveLogBookService intensivelogData, UserService userService){

        removeAll();
        add(menu);
        //database
        SimplelogData = simplelogData;
        ComprehensivelogData = comprehensivelogData;
        IntensivelogData = intensivelogData;
        this.userService = userService;

        //get patientuid using authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        User user= this.userService.getRepository().findByUsername(authentication.getName()); //return user
        patientUid=user.getUid();   //get patient uid

        //Session
        //get logbook type
        LogbookType=(String) VaadinSession.getCurrent().getAttribute("LogbookType");
        //get date
        SelectDate=(LocalDate) VaadinSession.getCurrent().getAttribute("CheckDate");

        //Back button
        add(Back);
        Back.addClickListener(click->Back.getUI().ifPresent(ui->ui.navigate(HistoryView.class)));

        //display simple log book
        if(LogbookType.equals("Simple")){
            add(
                    Back,
                    new HorizontalLayout(
                            new Paragraph("Date: "+SelectDate.toString()),
                            new Paragraph(LogbookType+"Log Book"))
            );
            //display data
            SimpleLogBookView(SimplelogData);

        }
        //display comprehensive log book
        else if(LogbookType.equals("Comprehensive")){
            LogbookType="Comprehensive";
            add(
                    Back,
                    new HorizontalLayout(
                            new Paragraph("Date: "+SelectDate.toString()),
                            new Paragraph(LogbookType+"Log Book"))
            );
            //display data
            CompLogBookView(ComprehensivelogData);
        }
        //display comprehensive log book
        else if(LogbookType.equals("Intensive")){
            LogbookType="Intensive";
            add(
                    Back,
                    new HorizontalLayout(
                            new Paragraph("Date: "+SelectDate.toString()),
                            new Paragraph(LogbookType+"Log Book"))
            );
            //display data
            IntensiveLogBookView(IntensivelogData);
        }
    }

    //display the data with intensive layout
    private void IntensiveLogBookView(IntensiveLogBookService IntensivelogData) {
        //find logbooks on that day
        List<IntensiveLogBook> ShowData=IntensivelogData.findLogByDateAndPatientuid(SelectDate,patientUid);
        //set up structure for each log
        for(IntensiveLogBook intensive:ShowData){
            //creat span for each data
            Span BloodGlucose = new Span("Blood Glucose : "+intensive.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+intensive.getCarbintake()+" g");
            Span InsulinDose = new Span("Insulin Dose : "+intensive.getInsulindose()+" unit");
            Span CarbBolus = new Span("Carb Bolus : "+intensive.getCarbbolus()+" unit");
            Span HighBSBolus = new Span("High BS Bolus : "+intensive.getHighbsbolus()+" unit");
            Span BasalRate = new Span("Basal Rate : "+intensive.getBasalrate()+" unit");
            Span Ketones = new Span("Ketones : "+intensive.getKetons()+" unit");

            //add all to a vertical layout
            VerticalLayout IntensiveLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose,CarbBolus,HighBSBolus,BasalRate,Ketones);
            //final layout
            Details IntensiveLogBook = new Details(intensive.getTime().toString(), IntensiveLog);
            add(IntensiveLogBook);
        }
    }

    //display the data with comprehensive layout
    private void CompLogBookView(ComprehensiveLogBookService ComprehensivelogData) {
        //find logbooks on that day
        List<ComprehensiveLogBook> ShowData=ComprehensivelogData.findLogByDateAndPatientuid(SelectDate,patientUid);
        //set up structure for each log
        for(ComprehensiveLogBook comprehensive:ShowData){
            //creat span for each data
            Span BloodGlucose = new Span("Blood Glucose : "+comprehensive.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+comprehensive.getCarbintake()+" g");
            Span InsulinDose = new Span("Insulin Dose  : "+comprehensive.getInsulindose()+" unit");

            //add all to a vertical layout
            VerticalLayout CompLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose);

            Details ComprehensiveLogBook = new Details(comprehensive.getTimeString(), CompLog);
            add(ComprehensiveLogBook);
        }
    }

    //display the data with simple layout
    public void SimpleLogBookView(SimpleLogBookService SimplelogData){
        //find logbooks on that day
        List<SimpleLogBook> ShowData= SimplelogData.findLogByDateAndPatientuid(SelectDate,patientUid);
        //set up structure for each log
        for (SimpleLogBook simpledata:ShowData) {
            //creat span for each data
            Span BloodGlucose = new Span("Blood Glucose : "+simpledata.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+simpledata.getCarbintake()+" g");

            //add all to a vertical layout
            VerticalLayout SimpleLog = new VerticalLayout(BloodGlucose, CarbonIntake);

            Details SimpleLogBook = new Details(new Paragraph(simpledata.getTimeString()), SimpleLog);
            add(SimpleLogBook);
        }
    }
}
