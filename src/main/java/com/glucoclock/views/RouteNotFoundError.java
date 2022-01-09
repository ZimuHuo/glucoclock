package com.glucoclock.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.server.VaadinSession;
import javax.servlet.http.HttpServletResponse;

/*
Exception handling when the user entered some random url does not exist
 */
@Tag(Tag.DIV)
public class RouteNotFoundError extends Component
        implements HasErrorParameter<NotFoundException> {
    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        String text = " Could not navigate to '"
                + event.getLocation().getPath()
                + "'. This URL does not exist!";
        getElement().setText(text);
        event.rerouteTo(Control.class);
        VaadinSession.getCurrent().setAttribute("Error",text);
        return HttpServletResponse.SC_NOT_FOUND;
    }
}