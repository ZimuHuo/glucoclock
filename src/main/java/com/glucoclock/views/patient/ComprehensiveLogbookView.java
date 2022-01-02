package com.glucoclock.views.patient;

import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import com.glucoclock.database.comprehensiveLogBook_db.service.ComprehensiveLogBookService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.MenuBar;
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

@PageTitle("Add Comprehensive Logbook Entry")
@Route(value = "patient/add-comprehensive-logbook-entry")
public class ComprehensiveLogbookView extends Div {
    private ComboBox<String> prepost;
    private ComboBox<String> meal;
    private TextField bloodGlucose;
    private TextField carbohydrate;
    private TextField insulinDose;
    private Button test1 = new Button("Test"); //Menubar test button
    private Button test2 = new Button("Test");
    private H3 title = new H3("Add Comprehensive Logbook Entry");
    private MenuBar menu = new MenuBar("PNS");

    private final UserService userService;
    private final ComprehensiveLogBookService comprehensiveLogBookService;


    public ComprehensiveLogbookView(UserService userService, ComprehensiveLogBookService comprehensiveLogBookService){
        this.userService = userService;
        this.comprehensiveLogBookService = comprehensiveLogBookService;
        init();
        add(menu);
        var formLayout = new FormLayout();
        formLayout.add(
                prepost, meal,
                bloodGlucose,
                carbohydrate,
                insulinDose
        );
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2)
        );

        formLayout.setColspan(bloodGlucose, 2);
        formLayout.setColspan(carbohydrate,2);
        formLayout.setColspan(insulinDose,2);
        formLayout.setMaxWidth("40%");
        Button submitButton = new Button("Upload");
        submitButton.setWidth("12%");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(e ->{
                    //check input validity
                    try{
                        float bg = Integer.parseInt(bloodGlucose.getValue());
                        float ci = Integer.parseInt(carbohydrate.getValue());
                        float id = Integer.parseInt(insulinDose.getValue());
                        //if blood glucose level is higher than the normal range, notify doctor via in-app notification and email
                        if(bg>140){
//                    SendMail sendMail = new SendMail();
//                    sendMail.sendMail("Act now","Glucose is high","Zimuhuo@outlook.com");
                            Notification.show("Abnormal Blood Glucose Level").addThemeVariants(NotificationVariant.LUMO_ERROR);//change to save to notification db later
                        }
                        //save to database
                        UUID uid = userService.getRepository().findAll().get(0).getUid();
                        ComprehensiveLogBook comprehensiveLogBook = new ComprehensiveLogBook(
                                uid,
                                (LocalDate) VaadinSession.getCurrent().getAttribute("date"),
                                prepost.getValue()+meal.getValue(),
                                bloodGlucose.getValue(),
                                carbohydrate.getValue(),
                                insulinDose.getValue()

                        );
                        comprehensiveLogBookService.getRepository().save(comprehensiveLogBook);

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
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,title,formLayout,submitButton);
        verticalLayout.setMargin(true);
        var horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(horizontalLayout);
    }

    private void init() {
        this.prepost = new ComboBox<>("Pre/Post");
        this.meal = new ComboBox<>("Meal");
        this.bloodGlucose = new TextField("Blood Glucose");
        this.carbohydrate = new TextField("Carbohydrate");
        this.insulinDose = new TextField("Insulin Dose");

        bloodGlucose.setHelperText("unit");
        carbohydrate.setHelperText("unit");
        insulinDose.setHelperText("unit");
        prepost.setItems("Pre","Post");
        meal.setItems("Breakfast","Lunch","Dinner");
    }

}
