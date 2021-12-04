package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Comprehensive LogBook View")
@Route(value = "ComprehensiveLogBookView",layout = MainLayout.class)
public class ComprehensiveLogBookView extends HorizontalLayout {
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private TextField bloodGlucose;
    private TextField carbohydrate;
    private TextField insulinDose;

    public ComprehensiveLogBookView(){
        init();
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
        formLayout.setColspan(carbohydrate,2 );
        formLayout.setColspan(insulinDose,2 );
        Button submitButton = new Button("Save");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new H3("Simple Logbook"));
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER,submitButton);
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);


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
}
