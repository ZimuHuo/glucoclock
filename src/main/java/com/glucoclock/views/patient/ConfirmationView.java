package com.glucoclock.views.patient;
import com.glucoclock.views.components.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("Upload Successful")
@Route(value = "patient/upload-confirmation")

public class ConfirmationView extends VerticalLayout{
    private Button OKButton;
    //private Icon Tick;
    private H2 Header ;
    private MenuBar menu = new MenuBar("PNS");


    public ConfirmationView(){

        Image tick = new Image("images/check.png","Upload Successful");
        tick.setHeight("160px");

        Header = new H2("Upload Successful!");

        OKButton = new Button("OK");
        OKButton .addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        OKButton .setWidth("15%");
        OKButton.setHeight("40px");
        OKButton.addClickListener(e ->
                OKButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientStartView.class)
                )
        );


        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, Header,tick,OKButton );

        add(menu, Header,tick,OKButton);
    }
}
