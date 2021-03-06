package com.glucoclock.views;

import com.glucoclock.views.components.MenuBar;
import com.glucoclock.views.doctor.DoctorSignUp1View;
import com.glucoclock.views.patient.PatientSignUp1View;
import com.glucoclock.views.researcher.ResearcherSignUp1View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign Up | Choose Account Type")
@Route(value = "sign-up")
public class SignUpView extends VerticalLayout{
    private Button PatientButton;
    private Button DoctorButton;
    private Button ResButton;
    private H2 Header ;
    private MenuBar menu = new MenuBar("NS");
    public SignUpView(){
        add (menu);
        Header = new H2("Choose your account type:");
        PatientButton = new Button("Patient");
        PatientButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        PatientButton.setWidth("30%");
        PatientButton.setHeight("50px");
        PatientButton.addClickListener(e ->{
            //VaadinSession.getCurrent().setAttribute( "Role","PATIENT");
            PatientButton.getUI().ifPresent(ui ->
                    ui.navigate(PatientSignUp1View.class)
            );
        }

        );
        DoctorButton = new Button(" Doctor");
        DoctorButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        DoctorButton.setWidth("30%");
        DoctorButton.setHeight("50px");
        DoctorButton.addClickListener(e ->{
            //VaadinSession.getCurrent().setAttribute( "Role","DOCTOR");
            DoctorButton.getUI().ifPresent(ui ->
                    ui.navigate(DoctorSignUp1View.class)
            );
        }

        );
        ResButton = new Button(" Researcher");
        ResButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        ResButton.setWidth("30%");
        ResButton.setHeight("50px");
        ResButton.addClickListener(e ->{
            //VaadinSession.getCurrent().setAttribute( "Role","RESEARCHER");
            ResButton.getUI().ifPresent(ui ->
                    ui.navigate(ResearcherSignUp1View.class)
            );
        }

        );

        setMargin(true);
        setSpacing(true);
        setHorizontalComponentAlignment(Alignment.CENTER, Header,PatientButton, DoctorButton,ResButton);

        add(Header,PatientButton,DoctorButton,ResButton);
    }
}
