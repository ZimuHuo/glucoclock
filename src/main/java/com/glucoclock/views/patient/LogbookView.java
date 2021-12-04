package com.glucoclock.views.patient;


import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.juli.logging.Log;

import java.time.LocalDate;
import java.util.ArrayList;

@PageTitle("LogBook")
@Route(value = "LogBook", layout = MainLayout.class)

public class LogbookView extends VerticalLayout {
    LocalDate SelectDate;
    String LogbookType="Simple";
    Icon editIcon=new Icon(VaadinIcon.EDIT);
    ArrayList<SimpleLogExample> SimpleData=new ArrayList<SimpleLogExample>();
    ArrayList<CompLogExample> Comprehensive=new ArrayList<CompLogExample>();
    ArrayList<IntensiveLogExample> Intensive=new ArrayList<IntensiveLogExample>();




    public LogbookView(){
    //Used only for testing remove when developing backends
        Button simple=new Button("Simple");
        Button comprehensive=new Button("Comprehensive");
        Button intensive=new Button("Intensive");

        add(new HorizontalLayout(simple,comprehensive,intensive));


        //sample input
            SimpleLogExample x1 = new SimpleLogExample(LocalDate.of(2021, 3, 8), "pre breakfast", 30, 30);
            SimpleLogExample x2 = new SimpleLogExample(LocalDate.of(2021, 3, 8), "after breakfast", 40, 10);
            SimpleLogExample x3 = new SimpleLogExample(LocalDate.of(2021, 3, 8), "pre lunch", 50, 20);
            SimpleData.add(x1);
            SimpleData.add(x2);
            SimpleData.add(x3);
            SelectDate = x1.getDate();
        //comprehensive input
            CompLogExample x4 = new CompLogExample(LocalDate.of(2021, 3, 8), "pre breakfast", 30, 30,40);
            CompLogExample x5 = new CompLogExample(LocalDate.of(2021, 3, 8), "after breakfast", 40, 10,50);
            CompLogExample x6 = new CompLogExample(LocalDate.of(2021, 3, 8), "pre lunch", 50, 20,60);
            Comprehensive.add(x4);
            Comprehensive.add(x5);
            Comprehensive.add(x6);
            SelectDate = x4.getDate();
        //intensive input
        IntensiveLogExample x7 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "9 AM", 30, 30,40,10,30,23);
        IntensiveLogExample x8 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "11 AM", 40, 10,50,54,65,23);
        IntensiveLogExample x9 = new IntensiveLogExample(LocalDate.of(2021, 3, 8), "2 PM", 50, 20,60,34,35,45);
        Intensive.add(x7);
        Intensive.add(x8);
        Intensive.add(x9);
        SelectDate = x7.getDate();

    //Don't remove this part, replace the button using input of this page
        //display simple log book
        simple.addClickListener(e->{
            LogbookType="Simple";
            Notification.show("Simple");
            removeAll();
            add(
                new HorizontalLayout(simple,comprehensive,intensive),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );
            SimpleLogBookView();
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
                new HorizontalLayout(simple,comprehensive,intensive),
                new HorizontalLayout(
                    new Paragraph("Date: "+SelectDate.toString()),
                    new Paragraph(LogbookType+"Log Book"),
                        editIcon)
                );
            CompLogBookView();
            editIcon.addClickListener(edit->Notification.show("edit"));
        });
        //display comprehensive log book
        intensive.addClickListener(e->{
            LogbookType="Intensive";
            Notification.show("Intensive");
            removeAll();
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
            Span BloodGlucose = new Span("Blood Glucose : "+intensive.getBloodGlucose().toString()+" unit");
            Span CarbonIntake = new Span("Carbon Intake : "+intensive.getCarbonIntake().toString()+" unit");
            Span CarbBolus = new Span("Carb Bolus : "+intensive.getCarbBolus().toString()+" unit");
            Span HighBSBolus = new Span("High BS Bolus : "+intensive.getHighBSBolus().toString()+" unit");
            Span BasalRange = new Span("Basal Range : "+intensive.getBasalRage().toString()+" unit");
            Span Ketones = new Span("High BS Bolus : "+intensive.getKetones().toString()+" unit");
            VerticalLayout IntensiveLog = new VerticalLayout(BloodGlucose, CarbonIntake,CarbBolus,HighBSBolus,BasalRange,Ketones);
            Details IntensiveLogBook = new Details(intensive.getTime(), IntensiveLog);
            add(IntensiveLogBook);
        }

    }

    private void CompLogBookView() {
        for(CompLogExample comprehensive:Comprehensive){
            Span BloodGlucose = new Span("Blood Glucose : "+comprehensive.getBloodGlucose().toString()+" unit");
            Span CarbonIntake = new Span("Carbon Intake : "+comprehensive.getCarbonIntake().toString()+" unit");
            Span InsulinDose = new Span("Insulin Dose  : "+comprehensive.getInsulinDose().toString()+" unit");
            VerticalLayout CompLog = new VerticalLayout(BloodGlucose, CarbonIntake,InsulinDose);
            Details SimpleLogBook = new Details(comprehensive.getTime(), CompLog);
            add(SimpleLogBook);
        }
    }

    public void SimpleLogBookView(){
        for (SimpleLogExample simpledata:SimpleData) {
            //Icon editSimple=new Icon(VaadinIcon.EDIT);
            Span BloodGlucose = new Span("Blood Glucose : "+simpledata.getBloodGlucose().toString()+" unit");
            Span CarbonIntake = new Span("Carbon Intake : "+simpledata.getCarbonIntake().toString()+" unit");
            VerticalLayout SimpleLog = new VerticalLayout(BloodGlucose, CarbonIntake);
            //bar contain time and edit icon
            //add edit icon
            //HorizontalLayout simplebar=new HorizontalLayout(new Paragraph(simpledata.getTime()),editSimple);
            Details SimpleLogBook = new Details(new Paragraph(simpledata.getTime()), SimpleLog);
            add(SimpleLogBook);
        }
    }

}
