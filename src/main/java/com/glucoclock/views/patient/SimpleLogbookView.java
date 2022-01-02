package com.glucoclock.views.patient;

import com.glucoclock.database.simpleLogBook_db.model.SimpleLogBook;
import com.glucoclock.database.simpleLogBook_db.service.SimpleLogBookService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.util.UUID;

@PageTitle("Add Simple Logbook Entry")
@Route(value = "patient/addsimplelogbookentry")
public class SimpleLogbookView extends Div {
    private H3 title = new H3("Add Simple Logbook Entry");
    ComboBox<String> prepost;
    ComboBox<String> meal;
    TextField bloodGlucose;
    TextField carbohydrate;
    Button submitButton = new Button("Upload");
    private MenuBar menu = new MenuBar("PNS");

    private final UserService userService;
    private final SimpleLogBookService simpleLogBookService;


    public SimpleLogbookView(UserService userService, SimpleLogBookService simpleLogBookService){
        this.userService = userService;
        this.simpleLogBookService = simpleLogBookService;

        init();
        add(menu);
        //add(menuBar());
        add(createFields());
    }

    private void init() {
        prepost = new ComboBox<>("Pre/Post");
        meal = new ComboBox<>("Meal");
        bloodGlucose = new TextField("Blood Glucose");
        carbohydrate = new TextField("Carbohydrate");

        bloodGlucose.setHelperText("unit");
        carbohydrate.setHelperText("unit");

        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");
    }


    private Component createFields(){
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2 );
        submitButton.setWidth("30%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e -> {
                    //check input validity
                    try{
                        float bg = Integer.parseInt(bloodGlucose.getValue());
                        float ci = Integer.parseInt(carbohydrate.getValue());
                        //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                        if(bg>140){
//                    SendMail sendMail = new SendMail();
//                    sendMail.sendMail("Act now","Glucose is high","Zimuhuo@outlook.com");
                            Notification.show("Abnormal Blood Glucose Level").addThemeVariants(NotificationVariant.LUMO_ERROR);//change to save to notification db later
                        }
                        //save to database
                        UUID uid = userService.getRepository().findAll().get(0).getUid();
                        SimpleLogBook simpleLogBook = new SimpleLogBook(
                                uid,
                                (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                                prepost.getValue()+meal.getValue(),
                                bloodGlucose.getValue(),
                                carbohydrate.getValue()
                        );
                        simpleLogBookService.getRepository().save(simpleLogBook);

                        //Navigation
                        submitButton.getUI().ifPresent(ui ->
                                ui.navigate(ConfirmationView.class)
                        );
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                        Notification.show("Invalid input(s), please re-enter");
                    }


                }

        );
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(title);
        verticalLayout.add(formLayout);
        verticalLayout.add(submitButton);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,submitButton);
        verticalLayout.setMaxWidth("40%");
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        return horizontalLayout;

    }
}