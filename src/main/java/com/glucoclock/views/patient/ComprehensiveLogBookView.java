package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Comprehensive LogBook View")
@Route(value = "ComprehensiveLogBookView",layout = MainLayout.class)
public class ComprehensiveLogBookView extends Div {
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private TextField bloodGlucose;
    private TextField carbohydrate;
    private TextField insulinDose;
    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");
    private H3 title = new H3("Add Comprehensive Logbook Entry");
    private MenuBar menu = new MenuBar("NS");


    public ComprehensiveLogBookView(){
        init();
        add(menu);
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate,
                insulinDose
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );

        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2);
        formLayout.setColspan(insulinDose,2);
        formLayout.setMaxWidth("40%");
        Button submitButton = new Button("Upload");
        submitButton.setWidth("12%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,formLayout,submitButton);
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout);
    }

    private void init() {
        this.prepost = new ComboBox<>("Pre/Post");
        this.meal = new ComboBox<>("Meal");
        this.bloodGlucose = new TextField("Blood Glucose");
        this.carbohydrate = new TextField("Carbohydrate");
        this.insulinDose = new TextField("Insulin Dose");

        bloodGlucose.setHelperText("unit");
        carbohydrate.setHelperText("unit");
        insulinDose.setHelperText("unit");
        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");
    }
    private Component menuBar(){
        this.setHeight("81px");
        this.getStyle().set( "background-image" , "url('images/menubar.png')");
        test1.setWidth("8%");
        test2.setWidth("8%");
        HorizontalLayout menuButtons = new HorizontalLayout(test1,test2);
        VerticalLayout rightC = new VerticalLayout();
        rightC.setHorizontalComponentAlignment(FlexComponent.Alignment.END,menuButtons);
        rightC.add(menuButtons);
        return rightC;
    }
}
