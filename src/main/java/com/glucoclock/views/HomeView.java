package com.glucoclock.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.Theme;

@Route(value = "login")
@Theme(themeFolder = "glucoclock")
public class HomeView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();
    private Button signUpBut;
    public HomeView() {
        //Logo
        Image logo = new Image("images/GC_logo.png","logo");
        logo.setWidth("500px");

        //Log in details
        login.setAction("login");
        login.addForgotPasswordListener(e ->
                signUpBut.getUI().ifPresent(ui ->
                        ui.navigate(ForgotPasswordView.class)
                ));

        //Sign up button
        signUpBut = new Button("Sign me up!");
        signUpBut.setWidth("310px");
        signUpBut.setHeight("50px");
        signUpBut.addClickListener(e ->
                signUpBut.getUI().ifPresent(ui ->
                        ui.navigate(SignUpView.class)
                )
        );

        add(logo,login, signUpBut);
        setAlignItems(Alignment.CENTER);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
