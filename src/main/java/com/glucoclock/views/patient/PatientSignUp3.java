package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp3",layout = MainLayout.class)
public class PatientSignUp3 extends HorizontalLayout {

    private Select<String> diabetesSelect;
    private CheckboxGroup<String> insulinSelect;
    private CheckboxGroup<String> injectionSelect;
    private Button submitButton;
    private Button previousButton;
    private VerticalLayout verticalLayout;
    private HorizontalLayout horizontalLayout;

    public PatientSignUp3() {
        init();
        submitButtonSetUp();
        previousButtonSetUp();
        verticalLayoutSetUp();
        horizontalLayoutSetUp();
        verticalLayoutAddComponent();
        this.add(verticalLayout);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void verticalLayoutAddComponent() {
        verticalLayout.add(
                diabetesSelect,
                insulinSelect,
                injectionSelect,
                horizontalLayout
                );
        verticalLayout.setMaxWidth("600px");
        verticalLayout.setPadding(false);
    }

    private void horizontalLayoutSetUp() {
        this.horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth(verticalLayout.getWidth());
        horizontalLayout.add(previousButton);
        horizontalLayout.add(submitButton);
    }

    private void verticalLayoutSetUp() {
        this.verticalLayout = new VerticalLayout();
        verticalLayout.setHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.add(new H1("Info about your diabetes"));
    }

    private void previousButtonSetUp() {
        this.previousButton = new Button("Previous");
        previousButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        previousButton.getElement().getStyle().set("margin-right", "auto");
    }

    private void submitButtonSetUp() {
        this.submitButton = new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
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
