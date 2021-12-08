package com.glucoclock.views.patient;


import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.views.MainLayout;
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
    Long patientId;         //set to 1 for testing
    LocalDate SelectDate;   //will get from previous page
    String LogbookType="Simple";
    Icon editIcon=new Icon(VaadinIcon.EDIT);

    ArrayList<CompLogExample> Comprehensive=new ArrayList<CompLogExample>();
    ArrayList<IntensiveLogExample> Intensive=new ArrayList<IntensiveLogExample>();
    private final SimpleLogBookService SimplelogData;
    private final ComprehensiveLogBookService ComprehensivelogData;




    public LogbookView(SimpleLogBookService SimplelogData, ComprehensiveLogBookService comprehensivelogData){

        this.SimplelogData = SimplelogData;
        ComprehensivelogData = comprehensivelogData;
        //Used only for testing remove when developing backends
        Button simple=new Button("Simple");
        Button comprehensive=new Button("Comprehensive");
        Button intensive=new Button("Intensive");

        add(new HorizontalLayout(simple,comprehensive,intensive));


//        //comprehensive input
//            CompLogExample x4 = new CompLogExample(LocalDate.of(2021, 3, 8), "pre breakfast", 30, 30,40);
//            CompLogExample x5 = new CompLogExample(LocalDate.of(2021, 3, 8), "after breakfast", 40, 10,50);
//            CompLogExample x6 = new CompLogExample(LocalDate.of(2021, 3, 8), "pre lunch", 50, 20,60);
//            Comprehensive.add(x4);
//            Comprehensive.add(x5);
//            Comprehensive.add(x6);

        //intensive input
        IntensiveLogExample x7 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "9 AM", 30, 30,40,10,30,23);
        IntensiveLogExample x8 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "11 AM", 40, 10,50,54,65,23);
        IntensiveLogExample x9 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "2 PM", 50, 20,60,34,35,45);
        Intensive.add(x7);
        Intensive.add(x8);
        Intensive.add(x9);


    //Don't remove this part, replace the button using input of this page
        //display simple log book
        simple.addClickListener(e->{
            LogbookType="Simple";
            Notification.show("Simple");
            removeAll();
            SelectDate=LocalDate.now();
            add(
                new HorizontalLayout(simple,comprehensive,intensive),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );
//try database here***enter data
            patientId=1L;
            SimplelogData.bulkcreate();

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
            SelectDate=LocalDate.now();
            add(
                new HorizontalLayout(simple,comprehensive,intensive),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );
//try database here***enter data
            patientId=1L;
            ComprehensivelogData.bulkcreate();

            CompLogBookView(ComprehensivelogData);
            editIcon.addClickListener(edit->Notification.show("edit"));
        });
        //display comprehensive log book
        intensive.addClickListener(e->{
            LogbookType="Intensive";
            Notification.show("Intensive");
            removeAll();
            SelectDate = x7.getDate();
            add(
            new HorizontalLayout(simple,comprehensive,intensive),
            new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                    editIcon)
            );
            IntensiveLogBookView();
            editIcon.addClickListener(edit->Notification.show("edit"));
        });



    }

    private void IntensiveLogBookView() {
        for(IntensiveLogExample intensive:Intensive){
            Span BloodGlucose = new Span("Blood Glucose : "+intensive.getBloodGlucose().toString()+" mmol/L");
            Span CarbonIntake = new Span("Carb Intake : "+intensive.getCarbonIntake().toString()+" g");
            Span CarbBolus = new Span("Carb Bolus : "+intensive.getCarbBolus().toString()+" unit");
            Span HighBSBolus = new Span("High BS Bolus : "+intensive.getHighBSBolus().toString()+" unit");
            Span BasalRange = new Span("Basal Range : "+intensive.getBasalRage().toString()+" unit");
            Span Ketones = new Span("High BS Bolus : "+intensive.getKetones().toString()+" unit");
            VerticalLayout IntensiveLog = new VerticalLayout(BloodGlucose, CarbonIntake,CarbBolus,HighBSBolus,BasalRange,Ketones);
            Details IntensiveLogBook = new Details(intensive.getTime(), IntensiveLog);
            add(IntensiveLogBook);
        }

    }

    private void CompLogBookView(ComprehensiveLogBookService ComprehensivelogData) {
        List<ComprehensiveLogBook> ShowData=ComprehensivelogData.findLogByDateAndPatientid(SelectDate,patientId);
        for(ComprehensiveLogBook comprehensive:ShowData){
            Span BloodGlucose = new Span("Blood Glucose : "+comprehensive.getBloodglucose().toString()+" unit");
            Span CarbonIntake = new Span("Carbon Intake : "+comprehensive.getCarbintake().toString()+" unit");
            Span InsulinDose = new Span("Insulin Dose  : "+comprehensive.getInsulindose().toString()+" unit");
            VerticalLayout CompLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose);
            Details SimpleLogBook = new Details(comprehensive.getTime(), CompLog);
            add(SimpleLogBook);
        }
    }

    public void SimpleLogBookView(SimpleLogBookService SimplelogData){
        List<SimpleLogBook> ShowData= SimplelogData.findLogByDateAndPatientid(SelectDate,patientId);
        for (SimpleLogBook simpledata:ShowData) {

            Span BloodGlucose = new Span("Blood Glucose : "+simpledata.getBloodglucose().toString()+" unit");
            Span CarbonIntake = new Span("Carbon Intake : "+simpledata.getCarbintake().toString()+" unit");
            VerticalLayout SimpleLog = new VerticalLayout(BloodGlucose, CarbonIntake);

            Details SimpleLogBook = new Details(new Paragraph(simpledata.getTime()), SimpleLog);
            add(SimpleLogBook);
        }
    }

}
