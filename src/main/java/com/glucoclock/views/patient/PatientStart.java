package com.glucoclock.views.patient;
import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.glucoclock.views.patient.ComprehensiveLogBookView;
import com.glucoclock.views.patient.IntensiveLogBookView;
import com.glucoclock.views.patient.PatientSignUp2;
import com.glucoclock.views.patient.SimpleLogBookView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("Patient Start Page")
@Route(value = "PatientStart",layout = MainLayout.class)

public class PatientStart extends VerticalLayout{
    private ComboBox<String> LBtybe;
    private DatePicker datePicker;
    private Icon update;
    private Button updateButton;
    private MenuBar menu = new MenuBar("PStart");
    public PatientStart(){
        String Logbook;
        add(menu);
        Image graph = new Image("images/GC_logo.png","logo");
        graph.setWidth("60%");
        //graph.setHeight("10%");

        LBtybe = new ComboBox<>();
        LBtybe.setLabel("Logbook\nType");
        LBtybe.setItems("Simple","Comprehensive","Intensive");
        LBtybe.addCustomValueSetListener(
                event -> LBtybe.setValue(event.getDetail()));

        datePicker = new DatePicker("Date");

        update = new Icon(VaadinIcon.UPLOAD);
        update.setSize("15%");
        updateButton = new Button(update);
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidth("150px");
        updateButton.setHeight("150px");

        LBtybe.addValueChangeListener(event -> {
            if (event.getValue() == "Simple") {
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(SimpleLogBookView.class)
                        )
                );
            } else if (event.getValue() == "Comprehensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(ComprehensiveLogBookView.class)
                        )
                );
            }
            else if (event.getValue() == "Intensive"){
                updateButton.addClickListener(e ->
                        updateButton.getUI().ifPresent(ui ->
                                ui.navigate(IntensiveLogBookView.class)
                        )
                );
            }
            //System.out.println(event.getValue());;
        });
        //setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER,graph,LBtybe,datePicker,updateButton);

        add(graph,LBtybe,datePicker,updateButton);

    }

}
