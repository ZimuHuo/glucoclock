package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
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

@PageTitle("Simple LogBook View")
@Route(value = "SimpleLogBookView",layout = MainLayout.class)
public class SimpleLogBookView extends Div {
    private H3 title = new H3("Add Simple Logbook Entry");
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private TextField bloodGlucose;
    private TextField carbohydrate;
    private Button submitButton = new Button("Upload");

    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");


    public SimpleLogBookView(){
        init();
        add(menuBar());
        add(createFields());
    }

    private void init() {
        prepost = new ComboBox<>("Pre/Post");
        meal = new ComboBox<>("Meal");
        bloodGlucose = new TextField("Blood Glucose");
        carbohydrate = new TextField("Carbohydrate");

        bloodGlucose.setHelperText("unit");
        carbohydrate.setHelperText("unit");

        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");
    }


    private Component menuBar(){
        this.setHeight("12.2%");
        this.getStyle().set( "background-image" , "url('images/menubar.png')");
        test1.setWidth("8%");
        test2.setWidth("8%");
        HorizontalLayout menuButtons = new HorizontalLayout(test1,test2);
        VerticalLayout rightC = new VerticalLayout();
        rightC.setHorizontalComponentAlignment(FlexComponent.Alignment.END,menuButtons);
        rightC.add(menuButtons);
        return rightC;
    }

    private Component createFields(){
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2 );
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return horizontalLayout;

    }
}
