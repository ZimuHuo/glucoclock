package com.glucoclock.views.doctor;

import com.glucoclock.security.db.User;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.glucoclock.views.patient.PatientSettings1View;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Change Password")
@Route(value = "doctor/change-password")

public class DoctorSettings2View extends HorizontalLayout{
    private H1 space = new H1(" ");
    PasswordField oldPassword, newPassword, confirmPassword;
    Button confirmButton, cancelButton;
    VerticalLayout mainLayout;
    private MenuBar menu = new MenuBar("DNS");

    private final UserService userService;

    public DoctorSettings2View(UserService userService) {
        this.userService = userService;

        add(menu);
        init();
        setJustifyContentMode(JustifyContentMode.CENTER);

        mainLayout.add(
                space,
                new H2("Change Password"),
                oldPassword,
                newPassword,
                confirmPassword,
                new HorizontalLayout(cancelButton, confirmButton)
        );

        add(mainLayout);
    }




    //    Initialize all components
    private void init() {
        mainLayoutInit();
        oldPasswordInit();
        confirmPasswordInit();
        newPasswordInit();
        confirmButtonInit();
        cancelButtonInit();
    }

    //    Following methods are used to initialize a single component
    private void mainLayoutInit() {
        mainLayout = new VerticalLayout();
        mainLayout.setMaxWidth("500px");
        mainLayout.setAlignItems(Alignment.STRETCH);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void oldPasswordInit() {
        oldPassword = new PasswordField("Current password");
        oldPassword.setClearButtonVisible(true);
        //oldPassword.setHelperText("Please enter your current password");
    }

    private void newPasswordInit() {
        newPassword = new PasswordField("New password");
        newPassword.setClearButtonVisible(true);
        newPassword.setPattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
        newPassword.setErrorMessage("Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters");
        newPassword.addValueChangeListener(e ->
                confirmPassword.setPattern(newPassword.getValue())
        );
        //newPassword.setHelperText("Please enter your new password");
    }

    private void confirmPasswordInit() {
        confirmPassword = new PasswordField("Confirm you new password");
        confirmPassword.setClearButtonVisible(true);
        //confirmPassword.setHelperText("Please enter you new password again");
    }

    private void confirmButtonInit() {
        confirmButton = new Button("Confirm");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        confirmButton.getElement().getStyle().set("margin-left", "auto");
        confirmButton.addClickListener(e -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    User user = userService.getRepository().findByUsername(authentication.getName());

                    if(user.checkPassword(oldPassword.getValue())){
                        if(!newPassword.isInvalid()) {
                            if(!confirmPassword.isInvalid()){
                                userService.updateUserPassword(authentication.getName(),newPassword.getValue());
                                authentication = new UsernamePasswordAuthenticationToken( user.getUsername(), newPassword.getValue(),
                                        AuthorityUtils.createAuthorityList("DOCTOR"));
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                                confirmButton.getUI().ifPresent(ui ->{
                                    ui.navigate(DoctorSettings1View.class);

                                });
                            } else {
                                Notification notification = Notification.show("You have entered a different password");
                                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                            }

                        }else {
                            Notification notification = Notification.show("Invalid password");
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        }

                    }else{
                        Notification notification = Notification.show("Wrong password");
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }
        );
    }

    private void cancelButtonInit() {
        cancelButton = new Button("Cancel");
        cancelButton.getElement().getStyle().set("margin-right", "auto");
        cancelButton.addClickListener(e ->
                cancelButton.getUI().ifPresent(ui ->
                        ui.navigate(DoctorSettings1View.class)
                )
        );
    }
}

