package com.glucoclock.data.entity;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.Element;
import org.apache.juli.logging.Log;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PersonData extends AbstractEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate Datadate;
    private String LogBookType;
    private String CompleteLogBook="-";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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
