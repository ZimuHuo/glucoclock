package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
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
public class SimpleLogBookView extends HorizontalLayout {
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private TextField bloodGlucose;
    private TextField carbohydrate;

    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");

    public SimpleLogBookView(){
        init();
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
        Button submitButton = new Button("Upload");
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new H3("Simple Logbook"));
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER,submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);


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
}
