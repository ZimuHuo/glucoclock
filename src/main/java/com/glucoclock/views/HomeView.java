package com.glucoclock.views;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

//    private TextField name;
//    private Button sayHello;
    private Button signInBut;
    private Button signUpBut;

    public HomeView() {
        //Logo
        Image logo = new Image("images/GC_logo.png","logo");
        logo.setWidth("50%");
        //Sign in button
        signInBut = new Button("Sign In");
        signInBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signInBut.setWidth("30%");
        //Sign up button
        signUpBut = new Button("Sign Up");
        signUpBut.setWidth("30%");

//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, logo,signInBut, signUpBut);

        add(logo,signInBut,signUpBut);
    }

}
