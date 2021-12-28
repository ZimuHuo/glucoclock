package com.glucoclock.security;

import com.glucoclock.views.HomeView;
import com.glucoclock.views.SignUp;
import com.glucoclock.views.doctor.DoctorSignUp1;
import com.glucoclock.views.doctor.DoctorSignUp2;
import com.glucoclock.views.patient.PatientSignUp1;
import com.glucoclock.views.patient.PatientSignUp2;
import com.glucoclock.views.patient.PatientSignUp3;
import com.glucoclock.views.researcher.ResearcherSignUp1;
import com.glucoclock.views.researcher.ResearcherSignUp2;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

/*
Restricting Access to Vaadin Views
Spring Security restricts access to content based on paths.
Vaadin applications are single-page applications.
This means that they do not trigger a full browser refresh
when you navigate between views, even though the path does change.
To secure a Vaadin application, you need to wire Spring Security
to the Vaadin navigation system.
 */

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
//        if (!HomeView.class.equals(event.getNavigationTarget()) //if nav target is not home view
//                && !SecurityUtils.isUserLoggedIn()) { //and if user is not logged in
//            event.rerouteTo(HomeView.class);
//        }
        //if nav target is not home view and user is not logged in
        if (!HomeView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()){
            //allow anonymous access to all sign-up pages
            if (SignUp.class.equals(event.getNavigationTarget())){
                event.forwardTo(SignUp.class);
            }
            else if (PatientSignUp1.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp1.class);
            }
            else if (PatientSignUp2.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp2.class);
            }
            else if (PatientSignUp3.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp3.class);
            }
            else if (DoctorSignUp1.class.equals(event.getNavigationTarget())){
                event.forwardTo(DoctorSignUp1.class);
            }
            else if (DoctorSignUp2.class.equals(event.getNavigationTarget())){
                event.forwardTo(DoctorSignUp2.class);
            }
            else if (ResearcherSignUp1.class.equals(event.getNavigationTarget())){
                event.forwardTo(ResearcherSignUp1.class);
            }
            else if (ResearcherSignUp2.class.equals(event.getNavigationTarget())){
                event.forwardTo(ResearcherSignUp2.class);
            }
            //else{event.rerouteTo(HomeView.class);}

        }


        }

    }

