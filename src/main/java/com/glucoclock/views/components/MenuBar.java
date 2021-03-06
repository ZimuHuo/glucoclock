package com.glucoclock.views.components;

import com.glucoclock.security.SecurityService;
import com.glucoclock.views.HomeView;
import com.glucoclock.views.doctor.DoctorNotificationView;
import com.glucoclock.views.doctor.DoctorSettings1View;
import com.glucoclock.views.doctor.DoctorStartView;
import com.glucoclock.views.patient.*;
import com.glucoclock.views.researcher.ResearcherSettings1View;
import com.glucoclock.views.researcher.ResearcherStartView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuBar extends AppLayout {
    private Icon cog = new Icon(VaadinIcon.COG_O);
    private Icon cal = new Icon(VaadinIcon.CALENDAR_CLOCK);
    private Icon signout = new Icon(VaadinIcon.SIGN_OUT);
    private Icon noti = new Icon(VaadinIcon.ENVELOPES);
    private Icon chart = new Icon(VaadinIcon.LINE_CHART);
    private Button history = new Button(cal);
    private Button settings = new Button(cog);
    private Button logout = new Button(signout);
    private Button notification = new Button(noti);
    private Button viewChart = new Button(chart);
    private Image logo = new Image("/images/logo_dark.png","logo");
    private Button home = new Button(logo);
    private Icon q = new Icon(VaadinIcon.QUESTION_CIRCLE);
    private Button qn = new Button(q);
    private final SecurityService securityService = new SecurityService();

    /*
    [pageType notes]
    NS: sign up views
    PNS: views that are not patient start views
    DNS: views that are not doctor start views
    RNS: views that are not researcher start views
    PStart: patient start view
    DStart: doctor start view
    RStart: researcher start view
    */

    public MenuBar(String pageType){
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout hlo = new HorizontalLayout();
        VerticalLayout vlo = new VerticalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        setStyles();

        //logout button
        logout.addClickListener(e -> securityService.logout());

        home.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        if (pageType == "PNS"){
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(PatientStartView.class)
                    )
            );
        }

        else if (pageType == "DNS"){
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(DoctorStartView.class)
                    )
            );
        }

        else if (pageType == "RNS"){
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(ResearcherStartView.class)
                    )
            );
        }

        else if (pageType == "NS"){
            home.addClickListener(e ->{
                getUI().get().getSession().close();
                home.getUI().ifPresent(ui ->
                        ui.navigate(HomeView.class)
                );
                    }

            );
        }

        if (pageType == "PStart"){
            hl.add(notification,history,viewChart,qn,settings,logout);
            viewChart.addClickListener(e ->
                    viewChart.getUI().ifPresent(ui ->
                            ui.navigate(PatientPlotView.class)
                    )
            );
            history.addClickListener(e ->
                    history.getUI().ifPresent(ui ->
                            ui.navigate(HistoryView.class)
                    )
            );
            qn.addClickListener(e ->
                    qn.getUI().ifPresent(ui ->
                            ui.navigate(QuestionnaireView.class)
                    )
            );
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate(PatientSettings1View.class)
                    )
            );
            notification.addClickListener(e ->
                    notification.getUI().ifPresent(ui ->
                            ui.navigate(PatientNotificationView.class)
                    )
            );
        }

        else if (pageType == "DStart"){
            hl.add(notification,settings,logout);
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate(DoctorSettings1View.class)
                    )
            );
            notification.addClickListener(e ->
                    notification.getUI().ifPresent(ui ->
                            ui.navigate(DoctorNotificationView.class)
                    )
            );
        }

        else if (pageType == "RStart"){
            hl.add(settings,logout);
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate(ResearcherSettings1View.class)
                    )
                    );
        }

        hl.setPadding(false);
        hl.setSpacing(false);

        hlo.add(home);
        vlo.add(hlo);
        vlo.setHorizontalComponentAlignment(FlexComponent.Alignment.START,home);

        vl.add(hl);
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.END,hl);
        vl.setPadding(false);
        hl2.add(vlo,vl);


        hl2.getStyle().set("height","70px")
                .set("width","100%")
                .set( "background-image" , "url('images/bgBar.png')")
                .set("margin", "0");


        addToNavbar(hl2);

    }

    public void setStyles(){
        //Logo
        logo.setHeight("65px");

        //Icons
        cog.setSize("45px");
        cog.setColor("white");
        cal.setSize("45px");
        cal.setColor("white");
        noti.setSize("45px");
        noti.setColor("white");
        signout.setSize("45px");
        signout.setColor("white");
        q.setSize("45px");
        q.setColor("white");
        chart.setSize("45px");
        chart.setColor("white");

        //Buttons
        viewChart.setWidth("65px");
        viewChart.setHeight("65px");
        notification.setWidth("65px");
        notification.setHeight("65px");
        qn.setHeight("65px");
        qn.setWidth("65px");
        settings.setWidth("65px");
        settings.setHeight("65px");
        logout.setWidth("65px");
        logout.setHeight("65px");
        history.setWidth("65px");
        history.setHeight("65px");
    }

}
