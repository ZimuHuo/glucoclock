package com.glucoclock.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;


import javax.imageio.ImageIO;
import javax.xml.transform.stream.StreamSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

@Route(value = "login")
@Theme(themeFolder = "glucoclock")
public class HomeView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();
//    private TextField email = new TextField("Email");
//    private PasswordField pw = new PasswordField("Password");
//    private Button signInBut;
    private Button signUpBut;
    private Button codeBut;
    //private H5 welcomeTxt = new H5("Your No.1 choice of \n modern digital diabetic logbook");
    TextArea textArea;
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
        codeBut = new Button("Get code");
        codeBut.addClickListener(e ->
                signUpBut.getUI().ifPresent(ui ->
                        ui.navigate(SignUp.class)
                )
        );

        textArea = new TextArea();
        textArea.setValue(getRandomNum());
        TextField textField = new TextField();

        add(logo,login,textArea,textField, signUpBut);

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
        if ((String)VaadinSession.getCurrent().getAttribute("code"))
    }



    public String getRandomNum(){
        Random random = new Random();
        String num = random.nextInt(99999999)+""; //nice trick for conversion in case you are wondering :D
        StringBuffer buffer = new StringBuffer();
        for (int i =0; i<7-num.length();i++){
            buffer.append(random.nextInt(9)); //took me a while to figure out this :D forced consistency
        }
        num = buffer.toString()+num;
        VaadinSession.getCurrent().setAttribute( "code",num);
        return num;

    }

}
