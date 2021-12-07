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
    private Button history = new Button(cal);
    private Button settings = new Button(cog);
    private Image logo = new Image("/images/logo_dark.png","logo");
    private Button home = new Button(logo);

    //private String width;

    public MenuBar(){
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        cog.setSize("40px");
        cal.setSize("40px");

        history.setWidth("60px");
        history.setHeight("60px");

        settings.setWidth("60px");
        settings.setHeight("60px");

        logo.setHeight("60px");
        home.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        hl.add(history,settings);
        hl.setPadding(false);
        hl.setSpacing(false);


        vl.add(hl);
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.END,hl);
        vl.setPadding(false);
        hl2.add(home,vl);
//        vl.add(home);
//        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.START,home);


        hl2.getStyle().set("height","63px")
                .set("width","100%")
                .set( "background-image" , "url('images/bgBar.png')")
                .set("margin", "0");


        addToNavbar(hl2);

    }


}
