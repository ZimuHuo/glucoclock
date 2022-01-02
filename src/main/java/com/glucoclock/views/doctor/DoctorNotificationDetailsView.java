package com.glucoclock.views.doctor;

import com.glucoclock.views.MenuBar;
import com.glucoclock.views.patient.ConfirmationView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Notification Details")
@Route(value = "doctor/notification-details")
public class DoctorNotificationDetailsView extends Div {
    private H3 title = new H3();
    private H5 msg = new H5("Notification details here");
    private TextArea replyMsg = new TextArea("Reply Here:");
    private Button sendBut = new Button("Send");
    private Button backBut = new Button("Back");
    private MenuBar menu = new MenuBar("DNS");

    public DoctorNotificationDetailsView(){
        setStyles();
        setNavigation();

        HorizontalLayout buttons = new HorizontalLayout(sendBut,backBut);

        VerticalLayout vl = new VerticalLayout(msg, replyMsg, buttons);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSpacing(true);

        add(menu,vl);
    }

    private void setStyles(){
        replyMsg.setWidth("50%");
        replyMsg.setMinHeight("80%");
        replyMsg.setMaxHeight("300px");
        replyMsg.setClearButtonVisible(true);
        sendBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backBut.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
    }

    private void setNavigation(){
        sendBut.addClickListener(e->{
            Notification.show("Reply sent").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    sendBut.getUI().ifPresent(ui ->
                            ui.navigate(DoctorNotificationView.class)
                    );
        });
        backBut.addClickListener(e->
                backBut.getUI().ifPresent(ui ->
                        ui.navigate(DoctorNotificationView.class)
                ));
    }
}
