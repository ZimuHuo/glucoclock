package com.glucoclock.views.researcher;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Start")
@Route(value = "Start", layout = MainLayout.class)
public class ResearcherStart extends VerticalLayout {
    Double F_AgeMin;
    Double F_AgeMax;
    String F_Gender;
    String F_Insulin;
    String F_Diabetes;

    public ResearcherStart(){
    //Buttons and blanks
        //Gender filter
        ComboBox<String> Filter_Gender = new ComboBox<>("Gender");
        Filter_Gender.setAllowCustomValue(true);
        Filter_Gender.setItems("Female","Male","Any");
        Filter_Gender.addValueChangeListener(gender-> {Notification.show(gender.getValue());F_Gender= gender.getValue();});

        //Age filter
        NumberField Filter_LowLim=new NumberField("Age from");
        NumberField Filter_HighLIm=new NumberField(" to ");
        Filter_LowLim.addValueChangeListener(e-> {F_AgeMin=e.getValue();Filter_HighLIm.setMin(F_AgeMin);Notification.show("Age Min: "+F_AgeMin.toString());});
        Filter_HighLIm.addValueChangeListener(e->{F_AgeMax=e.getValue();Filter_LowLim.setMax(F_AgeMax);Notification.show("Age Max: "+F_AgeMax.toString());});
        //Insulin filter
        ComboBox<String> Filter_Insulin = new ComboBox<>("Insulin type ");
        Filter_Insulin.setAllowCustomValue(true);
        Filter_Insulin.setItems("Rapid acting insulin","Short acting insulin","Intermediate acting insulin","Long acting insulin","Any");
        Filter_Insulin.addValueChangeListener(insulin-> {Notification.show(insulin.getValue());F_Insulin= insulin.getValue();});
        //Diabetes filter
        ComboBox<String> Filter_Diabetes = new ComboBox<>("Diabetes type ");
        Filter_Diabetes.setAllowCustomValue(true);
        Filter_Diabetes.setItems("Type I","Type II","Any");
        Filter_Diabetes.addValueChangeListener(diabetes-> {Notification.show(diabetes.getValue());F_Diabetes= diabetes.getValue();});
        //Button apply filter
        Button Filter_apply=new Button("Apply Filter");
        Filter_apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        //Download button
        Icon Filter_download=new Icon(VaadinIcon.DOWNLOAD);

    //Layout
        FormLayout ResearchFilter=new FormLayout();
        ResearchFilter.add(Filter_Gender,Filter_LowLim,Filter_HighLIm);
        ResearchFilter.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2),
                // Use three columns, if the layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 3)
        );
        ResearchFilter.add(Filter_Insulin,Filter_Diabetes,Filter_apply);

        VerticalLayout FinalLayout=new VerticalLayout(ResearchFilter,Filter_download);
        FinalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        Filter_download.addClickListener(download->Notification.show("Download: \nAge: "+F_AgeMin.toString()+"-"+F_AgeMax.toString()+"\nGender: "+F_Gender+"\nInsulin Type: "+F_Insulin+"\nDiabetes Type: "+F_Diabetes));

        add(FinalLayout);

    }
}
