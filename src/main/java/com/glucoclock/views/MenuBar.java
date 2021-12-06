package com.glucoclock.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

public class MenuBar extends AppLayout {
    public MenuBar(){
        H3 title = new H3("  ");
//        Button test1 = new Button("Test"); //Menubar test button
//        Button test2 = new Button("Test");

//        test1.setWidth("8%");
//        test2.setWidth("8%");
//        HorizontalLayout menuButtons = new HorizontalLayout(test1,test2);
        //menuButtons.setHeight("12.2%");
        title.getStyle().set( "background-image" , "url('images/menubar.png')")
                .set("margin", "0")
                .set("position", "absolute");
        Tab tab1 = new Tab("test");
        Tab tab2 = new Tab("test");
        Tabs tabs = new Tabs (tab1,tab2) ;
//        VerticalLayout rightC = new VerticalLayout();
//        rightC.setHorizontalComponentAlignment(FlexComponent.Alignment.END,menuButtons);
//        rightC.add(menuButtons);

        addToNavbar(title,tabs);

    }


}
