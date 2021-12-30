package com.glucoclock.views.patient;

import com.glucoclock.database.questionnaire_db.Questionnaire;
import com.glucoclock.database.questionnaire_db.QuestionnaireService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.UUID;


@PageTitle("Questionnaire View")
@Route(value = "QuestionnaireView")
public class QuestionnaireView extends Div {
    private H2 title = new H2 ("Questionnaire");
    private H5 text = new H5("To learn more about causes to your symptoms");
    private CheckboxGroup<String> symptoms = new CheckboxGroup<>();
    private TextField otherSymptoms = new TextField("Other Symptoms");
    private Button submit = new Button("Submit & View Feedback");
    private MenuBar menu = new MenuBar("PNS");

    private final UserService userService;
    private final QuestionnaireService questionnaireService;


    public QuestionnaireView(UserService userService, QuestionnaireService questionnaireService){
        this.userService = userService;
        this.questionnaireService = questionnaireService;
        symptoms.setLabel("Symptoms (Select all that apply)");
        symptoms.setItems("Frequent urination",
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
        submit.addClickListener(e->{
            UUID uid = userService.getRepository().findAll().get(0).getUid();
                    Questionnaire questionnaire = new Questionnaire(
                            uid,
                            LocalDateTime.now(),
                            symptoms.getValue().toString(),
                            otherSymptoms.getValue()
                    );
                    questionnaireService.getRepository().save(questionnaire);
            submit.getUI().ifPresent(ui ->
                            ui.navigate(PatientStart.class) //change to feedback page later
                    );

                }
                );
        symptoms.setWidth("100%");
        otherSymptoms.setWidth("100%");
        VerticalLayout specifySymptoms = new VerticalLayout();
        specifySymptoms.add(symptoms,otherSymptoms);
        specifySymptoms.setAlignItems(FlexComponent.Alignment.START);
        specifySymptoms.setWidth("50%");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(menu,title,text,specifySymptoms,submit);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
    }

}
