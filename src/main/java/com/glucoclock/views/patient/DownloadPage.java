package com.glucoclock.views.patient;


import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@PageTitle("Download")
@Route(value = "Download", layout = MainLayout.class)

public class DownloadPage extends VerticalLayout {
    Locale finnishLocale = new Locale("fi", "FI");
    DatePicker PrintStartDate = new DatePicker("Select start date:");
    DatePicker PrintEndDate = new DatePicker("Select end date:");
    Button ExportPlot=new Button("Export Plot");
    Button ExportData=new Button("Export Data");
    private MenuBar menu = new MenuBar("PNS");

    public DownloadPage() {
        add(menu);
        //2 calendar options preset to current time
        PrintStartDate.setLocale(finnishLocale);
        PrintStartDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintStartDate.setHelperText("Format: DD.MM.YYYY");
        PrintEndDate.setLocale(finnishLocale);
        PrintEndDate.setValue(LocalDate.now(ZoneId.systemDefault()));
        PrintEndDate.setHelperText("Format: DD.MM.YYYY");

        //add listener to calendar, make sure start date is before end date
        PrintStartDate.addValueChangeListener(e-> PrintEndDate.setMin(e.getValue()));
        PrintEndDate.addValueChangeListener(e-> PrintStartDate.setMax(e.getValue()));

        //Buttons
        ExportPlot.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        ExportData.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        ExportPlot.addClickListener(click->Notification.show("Download Plot form "+PrintStartDate.getValue()+" to "+PrintEndDate.getValue()));
        ExportData.addClickListener(click->Notification.show("Download Data form "+PrintStartDate.getValue()+" to "+PrintEndDate.getValue()));

        //Layout
        HorizontalLayout StartEndDate=new HorizontalLayout(PrintStartDate,PrintEndDate);
        VerticalLayout downLoadpage_layout=new VerticalLayout(new H2("Download"),StartEndDate,ExportPlot,ExportData);
        downLoadpage_layout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(downLoadpage_layout);
    }
}
