package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
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

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;


@PageTitle("Intensive LogBook View")
@Route(value = "IntensiveLogBookView",layout = MainLayout.class)
public class IntensiveLogBookView extends HorizontalLayout {
    TimePicker timePicker;
    TextField bloodGlucose;
    TextField carbohydrateIntake;
    TextField carbBolus;
    TextField highBsBolus;
    TextField basalRate;
    TextField ketones;
    int hour;

    public IntensiveLogBookView() {
        init();
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
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER,submitButton);
        verticalLayout.add(new H3("Intensive Logbook"));
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
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
}

