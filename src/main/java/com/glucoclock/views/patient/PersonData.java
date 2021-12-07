package com.glucoclock.views.patient;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.Element;
import org.apache.juli.logging.Log;

import javax.persistence.Entity;
import java.time.LocalDate;

public class PersonData {
    private LocalDate Datadate;
    private String LogBookType;
    private String CompleteLogBook="-";


    public void setDatadate(LocalDate Datadate){
        this.Datadate=Datadate;
    }
    public void setLogBookType(String LogBokType){
        this.LogBookType=LogBokType;
    }
    public void setCompleteLogBook(Boolean CompleteLogBook){
        if (CompleteLogBook==true)
            this.CompleteLogBook="Yes";
    }
    public String getLogBookType(){
        return LogBookType;
    }
    public LocalDate getDatadate(){
        return Datadate;
    }
    public Boolean getBooleanCompleteLogBook(){
        Boolean returnvalue=false;
        if(CompleteLogBook=="Yes") returnvalue=true;
        return returnvalue;
    }
    public String getCompleteLogBook(){
        return CompleteLogBook;
    }

}

