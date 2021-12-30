package com.glucoclock.views;

import com.glucoclock.views.patient.PatientStart;
import com.glucoclock.views.researcher.ResearcherSignUp1;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;

@PageTitle("Error")
@Route(value = "error/access-denied")
public class ErrorPage extends Div {
    private H3 errorMsg;
    private Button homeBut;

    public ErrorPage(){
        VerticalLayout vl = new VerticalLayout();
        errorMsg = new H3("Error: You don't have access to this page.");
        homeBut = new Button("Go back to Start Page");
        homeBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        //add navigation based on user role
//        homeBut.addClickListener(e->
//                homeBut.getUI().ifPresent(ui ->
//                        ui.navigate(PatientStart.class)
//                )
//
//        );
        vl.add(errorMsg,homeBut);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        add(vl);

    }
}
