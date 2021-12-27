package com.glucoclock.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.Theme;

@Route(value = "login")
@Theme(themeFolder = "glucoclock")
public class HomeView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();
//    private TextField email = new TextField("Email");
//    private PasswordField pw = new PasswordField("Password");
//    private Button signInBut;
    private Button signUpBut;
    //private H5 welcomeTxt = new H5("Your No.1 choice of \n modern digital diabetic logbook");

    public HomeView() {
        //Logo
        Image logo = new Image("images/GC_logo.png","logo");
        logo.setWidth("50%");
        //Log in details
        login.setAction("login");
//        email.setWidth("30%");
//        pw.setWidth("30%");
        //Sign in button
//        signInBut = new Button("Sign In");
//        signInBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        signInBut.setWidth("30%");
//        signInBut.setHeight("50px");
        //Sign up button
        signUpBut = new Button("Sign Up");
        signUpBut.setWidth("30%");
        signUpBut.setHeight("50px");
        signUpBut.addClickListener(e ->
                signUpBut.getUI().ifPresent(ui ->
                        ui.navigate(SignUp.class)
                )
        );


        add(logo,login,signUpBut);

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
