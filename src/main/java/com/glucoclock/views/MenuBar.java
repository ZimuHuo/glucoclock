package com.glucoclock.views;

import com.glucoclock.views.doctor.DoctorStartView;
import com.glucoclock.views.patient.HistoryView;
import com.glucoclock.views.patient.PatientStart;
import com.glucoclock.views.patient.QuestionnaireView;
import com.glucoclock.views.researcher.ResearcherStart;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class MenuBar extends AppLayout {
    private Icon cog = new Icon(VaadinIcon.COG_O);
    private Icon cal = new Icon(VaadinIcon.CALENDAR_CLOCK);
    private Icon signout = new Icon(VaadinIcon.SIGN_OUT);
    private Button history = new Button(cal);
    private Button settings = new Button(cog);
    private Button logout = new Button(signout);
    private Image logo = new Image("/images/logo_dark.png","logo");
    private Button home = new Button(logo);
    private Icon q = new Icon(VaadinIcon.QUESTION_CIRCLE);
    private Button qn = new Button(q);

    //pageType: PStart(patient start),DRStart(doctor/researcher start),NS(non-start)
    public MenuBar(String pageType){
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout hlo = new HorizontalLayout();
        VerticalLayout vlo = new VerticalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        cog.setSize("45px");
        cog.setColor("white");
        cal.setSize("45px");
        cal.setColor("white");
        signout.setSize("45px");
        signout.setColor("white");
        q.setSize("45px");
        q.setColor("white");

        history.setWidth("65px");
        history.setHeight("65px");
        history.addClickListener(e ->
                history.getUI().ifPresent(ui ->
                        ui.navigate(HistoryView.class)
                )
        );

        qn.setHeight("65px");
        qn.setWidth("65px");
        qn.addClickListener(e ->
                qn.getUI().ifPresent(ui ->
                        ui.navigate(QuestionnaireView.class)
                )
        );

        settings.setWidth("65px");
        settings.setHeight("65px");


        logout.setWidth("65px");
        logout.setHeight("65px");
        logout.addClickListener(e ->
                logout.getUI().ifPresent(ui ->
                        ui.navigate(HomeView.class)
                )
        );

        logo.setHeight("65px");
        home.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        if (pageType == "PNS"){
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(PatientStart.class)
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
                            ui.navigate(ResearcherStart.class)
                    )
            );
        }

        else if (pageType == "NS"){
            home.addClickListener(e ->
                    home.getUI().ifPresent(ui ->
                            ui.navigate(HomeView.class)
                    )
            );
        }


        if (pageType == "PStart"){
            hl.add(history,qn,settings,logout);
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate("PatientSetting1")
                    )
            );
        }

        else if (pageType == "DStart"){
            hl.add(settings,logout);
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate("DoctorSetting1")
                    )
            );}

        else if (pageType == "RStart"){
            hl.add(settings,logout);
            settings.addClickListener(e ->
                    settings.getUI().ifPresent(ui ->
                            ui.navigate("ResearcherSetting1")
                    )
                    );}

        hl.setPadding(false);
        hl.setSpacing(false);

        hlo.add(home);
        vlo.add(hlo);
        vlo.setHorizontalComponentAlignment(FlexComponent.Alignment.START,home);

        vl.add(hl);
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.END,hl);
        vl.setPadding(false);
        hl2.add(vlo,vl);
//        vl.add(home);
//        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.START,home);


        hl2.getStyle().set("height","70px")
                .set("width","100%")
                .set( "background-image" , "url('images/bgBar.png')")
                .set("margin", "0");


        addToNavbar(hl2);

    }


}
