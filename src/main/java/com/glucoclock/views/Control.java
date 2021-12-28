package com.glucoclock.views;

import com.glucoclock.security.db.Role;
import com.glucoclock.views.doctor.DoctorSignUp1;
import com.glucoclock.views.patient.PatientStart;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.Theme;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "")
@Theme(themeFolder = "glucoclock")
public class Control extends VerticalLayout implements BeforeEnterObserver {




    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("PATIENT"))){
            event.forwardTo(PatientStart.class);
            //add ifs
        }else{
            event.rerouteTo(HomeView.class);
        }
    }


}
