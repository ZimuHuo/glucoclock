package com.glucoclock.views;

import com.glucoclock.views.doctor.DoctorSignUp1;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route(value = "control")
public class Control extends UI {


    public Control(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().equals("PATIENT")){
            UI.getCurrent().navigate(DoctorSignUp1.class);
        }

    }

}
