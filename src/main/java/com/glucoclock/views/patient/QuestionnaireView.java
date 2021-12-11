package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Questionnaire View")
@Route(value = "QuestionnaireView",layout = MainLayout.class)
public class QuestionnaireView extends Div {
    private H2 title = new H2 ("Questionnaire");
    private CheckboxGroup<String> symptoms = new CheckboxGroup<>();
    private Button submit = new Button("Submit & View Feedback");
    private MenuBar menu = new MenuBar("PNS");


    public QuestionnaireView(){
        symptoms.setLabel("Symptoms (Select all that apply)");
        symptoms.setItems("Frequency urination",
                "Excessive thirst",
                "Unexplained weight loss",
                "Extreme hunger",
                "Sudden vision changes",
                "Tingling or numbness in hands or feet",
                "Very dry skin",
                "Sores that are slow to heal",
                "More infections than usual");
        symptoms.setWidth("50%");
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submit.setWidth("30%");
        submit.setHeight("50px");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(menu,title,symptoms,submit);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
    }

//    private Component selectSymp(){
//        VerticalLayout symptoms = new VerticalLayout();
//        symptoms.add(cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10);
//        symptoms.setAlignItems(FlexComponent.Alignment.START);
//        symptoms.setWidth("40%");
//        return symptoms;
//    }
}
