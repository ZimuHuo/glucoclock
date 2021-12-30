package com.glucoclock.views.patient;


import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.intensiveLogBook_db.model.IntensiveLogBook;
import com.glucoclock.database.intensiveLogBook_db.service.IntensiveLogBookService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.views.MainLayout;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@PageTitle("LogBook")
@Route(value = "LogBook", layout = MainLayout.class)

public class LogbookView extends VerticalLayout {
    Long patientId=1L;         //set to 1 for testing
    LocalDate SelectDate=LocalDate.now();   //will get from previous page
    String LogbookType="Simple";
    Icon editIcon=new Icon(VaadinIcon.EDIT);
    ArrayList<SimpleLogExample> SimpleData=new ArrayList<SimpleLogExample>();
    ArrayList<CompLogExample> Comprehensive=new ArrayList<CompLogExample>();
    ArrayList<IntensiveLogExample> Intensive=new ArrayList<IntensiveLogExample>();
    private MenuBar menu = new MenuBar("PNS");

    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;
    private final IntensiveLogBookService IntensivelogData;
    private Button Back=new Button("Back");



    public LogbookView(SimpleLogBookService simplelogData, ComprehensiveLogBookService comprehensivelogData, IntensiveLogBookService intensivelogData){
        removeAll();
            add(menu);
            //Used only for testing remove when developing backends
//try set up database
        SimplelogData = simplelogData;
        ComprehensivelogData = comprehensivelogData;
        IntensivelogData = intensivelogData;
        //Used only for testing remove when developing backends
        Button simple=new Button("Simple");
        Button comprehensive=new Button("Comprehensive");
        Button intensive=new Button("Intensive");

        add(new HorizontalLayout(simple,comprehensive,intensive,Back));

//load data into the database
//        System.out.println("active");
//        SimplelogData.bulkcreate();
//        ComprehensivelogData.bulkcreate();
//        IntensivelogData.bulkcreate();

        Back.addClickListener(click->Back.getUI().ifPresent(ui->ui.navigate(HistoryView.class)));

    //Don't remove this part, replace the button using input of this page
        //display simple log book
        simple.addClickListener(e->{
            LogbookType="Simple";
            Notification.show("Simple");
            removeAll();
            add(
                new HorizontalLayout(simple,comprehensive,intensive,Back),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );

            SimpleLogBookView(SimplelogData);
            editIcon.addClickListener(edit->{
                Notification.show("edit");
            });

        });
        //display comprehensive log book
        comprehensive.addClickListener(e->{
            LogbookType="Comprehensive";
            Notification.show("Comprehensive");
            removeAll();
            add(
                new HorizontalLayout(simple,comprehensive,intensive,Back),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );


            CompLogBookView(ComprehensivelogData);
            editIcon.addClickListener(edit->Notification.show("edit"));
        });
        //display comprehensive log book
        intensive.addClickListener(e->{
            LogbookType="Intensive";
            Notification.show("Intensive");
            removeAll();
            add(
            new HorizontalLayout(simple,comprehensive,intensive,Back),
            new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                    editIcon)
            );

            IntensiveLogBookView(IntensivelogData);
            editIcon.addClickListener(edit->Notification.show("edit"));
        });



    }

    private void IntensiveLogBookView(IntensiveLogBookService IntensivelogData) {
        List<IntensiveLogBook> ShowData=IntensivelogData.findLogByDateAndPatientid(SelectDate,patientId);
        for(IntensiveLogBook intensive:ShowData){
            Span BloodGlucose = new Span("Blood Glucose : "+intensive.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+intensive.getCarbintake()+" g");
            Span InsulinDose = new Span("Insulin Dose : "+intensive.getInsulindose()+" unit");
            Span Food = new Span("Carb Bolus : "+intensive.getCarbbolus()+" unit");
            Span ExerciseType = new Span("High BS Bolus : "+intensive.getHighbsbolus()+" unit");
            Span ExerciseDuration = new Span("Basal Rate : "+intensive.getBasalrate()+" unit");
            Span UnusualEvent = new Span("Ketones : "+intensive.getKetons()+" unit");
            VerticalLayout IntensiveLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose,Food,ExerciseType,ExerciseDuration,UnusualEvent);
            Details IntensiveLogBook = new Details(intensive.getTime().toString(), IntensiveLog);
            add(IntensiveLogBook);
        }

    }

    private void CompLogBookView(ComprehensiveLogBookService ComprehensivelogData) {
        List<ComprehensiveLogBook> ShowData=ComprehensivelogData.findLogByDateAndPatientid(SelectDate,patientId);
        for(ComprehensiveLogBook comprehensive:ShowData){
            Span BloodGlucose = new Span("Blood Glucose : "+comprehensive.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+comprehensive.getCarbintake()+" g");
            Span InsulinDose = new Span("Insulin Dose  : "+comprehensive.getInsulindose()+" unit");
            VerticalLayout CompLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose);
            Details ComprehensiveLogBook = new Details(comprehensive.getTimeString(), CompLog);
            add(ComprehensiveLogBook);
        }
    }

    public void SimpleLogBookView(SimpleLogBookService SimplelogData){
        List<SimpleLogBook> ShowData= SimplelogData.findLogByDateAndPatientid(SelectDate.minusDays(8),patientId);
        for (SimpleLogBook simpledata:ShowData) {

            Span BloodGlucose = new Span("Blood Glucose : "+simpledata.getBloodglucose()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+simpledata.getCarbintake()+" g");
            VerticalLayout SimpleLog = new VerticalLayout(BloodGlucose, CarbonIntake);

            Details SimpleLogBook = new Details(new Paragraph(simpledata.getTimeString()), SimpleLog);
            add(SimpleLogBook);
        }
    }

}
