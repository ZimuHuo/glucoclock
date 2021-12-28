package com.glucoclock.security;

import com.glucoclock.views.HomeView;
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
        if (!HomeView.class.equals(event.getNavigationTarget()) //if nav target is not home view
                && !SecurityUtils.isUserLoggedIn()) { //and if user is not logged in
            event.rerouteTo(HomeView.class);
        }
    }
}
