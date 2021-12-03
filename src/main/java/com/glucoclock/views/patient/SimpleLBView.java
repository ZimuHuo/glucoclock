package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Update Simple Logbook")
@Route(value = "simple_lb", layout = MainLayout.class)
@Uses(Icon.class)
public class SimpleLBView extends Div {
    private H3 title = new H3("Simple Logbook");
    private TextField gluc = new TextField("Blood Glucose");
    private TextField carbs = new TextField("Carbohydrate Intake");
    private ComboBox<String> prepost = new ComboBox<>("Pre/Post");
    private ComboBox<String> meal = new ComboBox<>("Meal");
    private Button upload = new Button("Upload");


    public SimpleLBView(){

        Image logo = new Image("images/menubar.png","logo");
        logo.setWidth("100%");

        add(logo);
        add(createFields());


    }

    private Component createFields() {
        //Time picker
        prepost.setWidth("35%");
        prepost.setItems("Pre","Post");
        meal.setWidth("65%");
        meal.setItems("Breakfast","Lunch","Dinner");
        HorizontalLayout time = new HorizontalLayout();
        time.setVerticalComponentAlignment(FlexComponent.Alignment.START,prepost,meal);
        time.setMargin(true);
        time.add(prepost,meal);
        //The rest
        gluc.setWidth("39%");
        carbs.setWidth("39%");
        upload.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        upload.setWidth("25%");
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,time,gluc,carbs,upload);
        layout.add(title,time,gluc,carbs,upload);
        return layout;
    }





}
