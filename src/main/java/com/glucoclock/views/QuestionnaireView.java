package com.glucoclock.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Questionnaire View")
@Route(value = "QuestionnaireView",layout = MainLayout.class)
public class QuestionnaireView extends Div {
    private H2 title = new H2 ("Questionnaire");
    private H5 text = new H5("Symptoms (Select all that apply)");
    private Checkbox cb1 = new Checkbox("Frequency urination");
    private Checkbox cb2 = new Checkbox("Excessive thirst");
    private Checkbox cb3 = new Checkbox("Unexplained weight loss");
    private Checkbox cb4 = new Checkbox("Extreme hunger");
    private Checkbox cb5 = new Checkbox("Sudden vision changes");
    private Checkbox cb6 = new Checkbox("Tingling or numbness in hands or feet");
    private Checkbox cb7 = new Checkbox("Feeling very tired much of the time");
    private Checkbox cb8 = new Checkbox("Very dry skin");
    private Checkbox cb9 = new Checkbox("Sores that are slow to heal");
    private Checkbox cb10 = new Checkbox("More infections than usual");
    private Button submit = new Button("Submit & View Feedback");
    private MenuBar menu = new MenuBar("NS");


    public QuestionnaireView(){
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submit.setWidth("30%");
        submit.setHeight("50px");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(menu,title,selectSymp(),submit);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
    }

    private Component selectSymp(){
        VerticalLayout symptoms = new VerticalLayout();
        symptoms.add(text,cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10);
        symptoms.setAlignItems(FlexComponent.Alignment.START);
        symptoms.setWidth("40%");
        return symptoms;
    }
}
