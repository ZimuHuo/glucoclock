package com.glucoclock.views;

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

    //private String width;

    public MenuBar(){
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


        history.setWidth("65px");
        history.setHeight("65px");

        settings.setWidth("65px");
        settings.setHeight("65px");

        logout.setWidth("65px");
        logout.setHeight("65px");

        logo.setHeight("65px");
        home.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        hl.add(history,settings,logout);
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
