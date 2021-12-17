package com.glucoclock.views.patient;

import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;


@PageTitle("Intensive LogBook View")
@Route(value = "IntensiveLogBookView")
public class IntensiveLogBookView extends Div {
    private TimePicker timePicker;
    private TextField bloodGlucose;
    private TextField carbohydrateIntake;
    private TextField carbBolus;
    private TextField highBsBolus;
    private TextField basalRate;
    private TextField ketones;
    private int hour;
    private H3 title = new H3("Add Intensive Logbook Entry");
    private MenuBar menu = new MenuBar("PNS");

    public IntensiveLogBookView() {
        init();
        add(menu);
        //add(menuBar());
        var formLayout = new FormLayout();
        formLayout.add(
                timePicker,
                bloodGlucose,
                carbohydrateIntake,
                carbBolus,
                highBsBolus,
                basalRate,
                ketones
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 1)
        );

        formLayout.setColspan(timePicker, 1);
        formLayout.setColspan(bloodGlucose,1 );
        formLayout.setColspan(carbohydrateIntake,1 );
        formLayout.setColspan(carbBolus,1 );
        formLayout.setColspan(highBsBolus,1 );
        formLayout.setColspan(basalRate,1 );
        formLayout.setColspan(basalRate,1 );
        formLayout.setColspan(ketones,1);
        Button submitButton = new Button("Upload");
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e ->
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(ConfirmationPage.class)
                )
        );
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,submitButton);
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout);
    }

    private void init() {
        this.timePicker = new TimePicker("Time");
        setTimePicker();
        this.bloodGlucose = new TextField("Blood Glucose");
        this.carbohydrateIntake = new TextField("Carbohydrate Intake");
        this.carbBolus = new TextField("Carb Bolus");
        this.highBsBolus = new TextField("High BS Bolus");
        this.basalRate = new TextField("Basal Rate");
        this.ketones = new TextField("Ketones");
        setClearButtonVisible();
        setHelperText();
    }

    private void setTimePicker() {
        timePicker.setStep(Duration.ofHours(1));
        ZonedDateTime now = ZonedDateTime.now();
        if(now.getMinute()<=30){
            hour = now.getHour();
        }else{
            if(now.getHour()<23){
                hour = now.getHour()+1;
            }
        }
        timePicker.setValue(LocalTime.of(hour, 0));
    }

    private void setHelperText() {
        bloodGlucose.setHelperText("unit");
        carbohydrateIntake.setHelperText("unit");
        carbBolus.setHelperText("unit");
        highBsBolus.setHelperText("unit");
        basalRate.setHelperText("unit");
        ketones.setHelperText("unit");
    }

    private void setClearButtonVisible() {
        bloodGlucose.setClearButtonVisible(true);
        carbohydrateIntake.setClearButtonVisible(true);
        carbBolus.setClearButtonVisible(true);
        highBsBolus.setClearButtonVisible(true);
        basalRate.setClearButtonVisible(true);
        ketones.setClearButtonVisible(true);
    }

    private Component menuBar(){
        Button test1 = new Button("Test"); //Menubar test button
        Button test2 = new Button("Test");
        this.setHeight("81px");
        this.getStyle().set( "background-image" , "url('images/menubar.png')")
                .set("margin", "0")
                .set("position", "absolute");
        test1.setWidth("8%");
        test2.setWidth("8%");
        HorizontalLayout menuButtons = new HorizontalLayout(test1,test2);
        VerticalLayout rightC = new VerticalLayout();
        rightC.setHorizontalComponentAlignment(FlexComponent.Alignment.END,menuButtons);
        rightC.add(menuButtons);
        return rightC;
    }

}

