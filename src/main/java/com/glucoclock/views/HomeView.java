package com.glucoclock.views;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {


private TextField email = new TextField("Email");
    private PasswordField pw = new PasswordField("Password");
    private Button signInBut;
    private Button signUpBut;
    //private H5 welcomeTxt = new H5("Your No.1 choice of \n modern digital diabetic logbook");

    public HomeView() {
        //Logo
        Image logo = new Image("images/GC_logo.png","logo");
        logo.setWidth("50%");
        //Log in details
        email.setWidth("30%");
        pw.setWidth("30%");
        //Sign in button
        signInBut = new Button("Sign In");
        signInBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signInBut.setWidth("30%");
        signInBut.setHeight("50px");
        //Sign up button
        signUpBut = new Button("Don't have an account?\nSign Up");
        signUpBut.setWidth("30%");
        signUpBut.setHeight("50px");
        signUpBut.addClickListener(e ->
                signUpBut.getUI().ifPresent(ui ->
                        ui.navigate(SignUp.class)
                )
        );


        add(logo,email,pw,signInBut,signUpBut);
        setAlignItems(Alignment.CENTER);

    }

}
