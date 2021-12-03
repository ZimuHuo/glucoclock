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


@PageTitle("Upload Intensive Logbook")
@Route(value = "intensive_lb", layout = MainLayout.class)
@Uses(Icon.class)
public class IntensiveLBView extends Div{

    private H3 title = new H3("Intensive Logbook");
    private TextField gluc = new TextField("Blood Glucose");
    private TextField carbs = new TextField("Carbohydrate Intake");
    private TextField carbBo = new TextField("Carb Bolus");
    private TextField highBS = new TextField("High BS Bolus");
    private TextField basal = new TextField("Basal Rate");
    private TextField ketones = new TextField("Ketones");
    private ComboBox<String> prepost = new ComboBox<>("Time");
    private ComboBox<String> meal = new ComboBox<>("AM/PM");
    private Button upload = new Button("Upload");
    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");

    public IntensiveLBView(){
        add(menuBar());
        add(createFields());
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

    private Component createFields() {
        //Time picker
        prepost.setWidth("50%");
        prepost.setItems("00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00");
        meal.setWidth("50%");
        meal.setItems("AM","PM");
        HorizontalLayout time = new HorizontalLayout();
        time.setVerticalComponentAlignment(FlexComponent.Alignment.START,prepost,meal);
        time.setMargin(true);
        time.add(prepost,meal);
        //The rest
        gluc.setWidth("39%");
        carbs.setWidth("39%");
        carbBo.setWidth("39%");
        highBS.setWidth("39%");
        basal.setWidth("39%");
        ketones.setWidth("39%");
        upload.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        upload.setWidth("25%");
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,time,gluc,carbs,carbBo,highBS,basal,ketones,upload);
        layout.add(title,time,gluc,carbs,carbBo,highBS,basal,ketones,upload);
        return layout;
    }
}

