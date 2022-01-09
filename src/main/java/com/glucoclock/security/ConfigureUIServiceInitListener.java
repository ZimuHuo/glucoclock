package com.glucoclock.security;

import com.glucoclock.views.AccessDenialView;
import com.glucoclock.views.HomeView;
import com.glucoclock.views.SignUpView;
import com.glucoclock.views.doctor.DoctorSignUp1View;
import com.glucoclock.views.doctor.DoctorSignUp2View;
import com.glucoclock.views.ForgotPasswordView;
import com.glucoclock.views.patient.PatientSignUp1View;
import com.glucoclock.views.patient.PatientSignUp2View;
import com.glucoclock.views.patient.PatientSignUp3View;
import com.glucoclock.views.researcher.ResearcherSignUp1View;
import com.glucoclock.views.researcher.ResearcherSignUp2View;
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
        //if navigation target is not home view and user is not logged in
        if (!HomeView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()){
            //allow anonymous access to all sign up views, error views, and forgot password view
            if (SignUpView.class.equals(event.getNavigationTarget())){
                event.forwardTo(SignUpView.class);
            }
            else if (PatientSignUp1View.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp1View.class);
            }
            else if (PatientSignUp2View.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp2View.class);
            }
            else if (PatientSignUp3View.class.equals(event.getNavigationTarget())){
                event.forwardTo(PatientSignUp3View.class);
            }
            else if (DoctorSignUp1View.class.equals(event.getNavigationTarget())){
                event.forwardTo(DoctorSignUp1View.class);
            }
            else if (DoctorSignUp2View.class.equals(event.getNavigationTarget())){
                event.forwardTo(DoctorSignUp2View.class);
            }
            else if (ResearcherSignUp1View.class.equals(event.getNavigationTarget())){
                event.forwardTo(ResearcherSignUp1View.class);
            }
            else if (ResearcherSignUp2View.class.equals(event.getNavigationTarget())){
                event.forwardTo(ResearcherSignUp2View.class);
            }
            else if (ForgotPasswordView.class.equals(event.getNavigationTarget())){
                event.forwardTo(ForgotPasswordView.class);
            }
            else if (AccessDenialView.class.equals(event.getNavigationTarget())){
                event.forwardTo(AccessDenialView.class);
            }
            //Reroute to home view otherwise
            else{event.rerouteTo(HomeView.class);}

        }

        }

    }

