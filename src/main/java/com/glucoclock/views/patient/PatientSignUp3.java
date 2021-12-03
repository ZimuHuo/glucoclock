package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;

import java.time.LocalDate;
import java.time.ZoneId;



@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp3",layout = MainLayout.class)
public class PatientSignUp3 extends HorizontalLayout {

    Select<String> diabetesSelect;
    CheckboxGroup<String> insulinSelect;
    CheckboxGroup<String> injectionSelect;

    public PatientSignUp3() {
        init();

        Button submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");

        Button previousButton = new Button("Previous");
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.add(new H1("Info about your diabetes"));

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth(verticalLayout.getWidth());
        buttons.add(previousButton);
        buttons.add(submitButton);

        verticalLayout.add(
                diabetesSelect,
                insulinSelect,
                injectionSelect,
                buttons
                );


        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
        add(verticalLayout);

        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void init() {
        diabetesSelectSetUp();
        insulinSelectSetUp();
        injectionSelectSetUp();
    }



    private void diabetesSelectSetUp() {
        diabetesSelect = new Select<>("Type I","Type II");
        diabetesSelect.setLabel("Type of diabetes");
    }

    private void insulinSelectSetUp() {
        insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin", "Short-acting insulin", "Intermediate-acting insulin", "Long-acting insulin");
        insulinSelect.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
    }

    private void injectionSelectSetUp() {
        injectionSelect = new CheckboxGroup<>();
        injectionSelect.setLabel("Injection method");
        injectionSelect.setItems("Syringe", "Injection pen", "Insulin pump");
    }
}
