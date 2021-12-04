package com.glucoclock.data.entity;

import com.glucoclock.data.AbstractEntity;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.Element;
import org.apache.juli.logging.Log;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class PersonData extends AbstractEntity {
    private LocalDate Datadate;
    private String LogBookType;
    private Boolean CompleteLogBook=false;


    public void setDatadate(LocalDate Datadate){
        this.Datadate=Datadate;
    }
    public void setLogBookType(String LogBokType){
        this.LogBookType=LogBokType;
    }
    public void setCompleteLogBook(Boolean CompleteLogBook){
        this.CompleteLogBook=CompleteLogBook;
    }
    public String getLogBookType(){
        return LogBookType;
    }
    public LocalDate getDatadate(){
        return Datadate;
    }
    public String getCompleteLogBook(){
        String returnString;
        if(CompleteLogBook==true) returnString="YES";
        else returnString="-";
        return returnString;
    }

}
