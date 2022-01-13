package com.glucoclock.views.util;

import com.glucoclock.views.HomeView;
import com.glucoclock.views.doctor.DoctorStartView;
import com.glucoclock.views.patient.PatientStartView;
import com.glucoclock.views.researcher.ResearcherStartView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


/*
It is a control hub which reroute the users after sign up and re-route them in case of RouteNotFoundError
 */
@Route(value = "")
@Theme(themeFolder = "glucoclock")
public class Control extends VerticalLayout implements BeforeEnterObserver {
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("PATIENT"))){
            event.forwardTo(PatientStartView.class);
        }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("DOCTOR"))){
            event.forwardTo(DoctorStartView.class);
        }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("RESEARCHER"))){
            event.forwardTo(ResearcherStartView.class);
        }else{
            event.rerouteTo(HomeView.class);
        }
        //  a fully functional error page to deal with all the exceptions
    }
}
