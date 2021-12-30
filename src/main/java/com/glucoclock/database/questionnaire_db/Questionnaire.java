package com.glucoclock.database.questionnaire_db;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Questionnaire_db")
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="uid")
    private UUID uid;

    @Column(name="DateTime")
    private LocalDateTime dateTime;

    @Column(name="FrequentUrination")
    private Boolean isFrequentUrination;

    @Column(name="ExcessiveThirst")
    private Boolean isExcessiveThirst;

    @Column(name="UnexplainedWeightLoss")
    private Boolean isUnexplainedWeightLoss;

    @Column(name="ExtremeHunger")
    private Boolean isExtremeHunger;

    @Column(name="SuddenVisionChanges")
    private Boolean isSuddenVisionChanges;

    @Column(name="TinglingOrNumbnessInHandsOrFeet")
    private Boolean isTinglingOrNumbnessInHandsOrFeet;

    @Column(name="VeryDrySkin")
    private Boolean isVeryDrySkin;

    @Column(name="SoresThatAreSlowToHeal")
    private Boolean isSoresThatAreSlowToHeal;

    @Column(name="MoreInfectionsThanUsual")
    private Boolean isMoreInfectionsThanUsual;

    public Questionnaire(UUID uid,
                         LocalDateTime dateTime,
                         Boolean isFrequentUrination,
                         Boolean isExcessiveThirst,
                         Boolean isUnexplainedWeightLoss,
                         Boolean isExtremeHunger,
                         Boolean isSuddenVisionChanges,
                         Boolean isTinglingOrNumbnessInHandsOrFeet,
                         Boolean isVeryDrySkin,
                         Boolean isSoresThatAreSlowToHeal,
                         Boolean isMoreInfectionsThanUsual){
        this.uid = uid;
        this.dateTime = dateTime;
        this.isFrequentUrination = isFrequentUrination;
        this.isExcessiveThirst = isExcessiveThirst;
        this.isUnexplainedWeightLoss = isUnexplainedWeightLoss;
        this.isExtremeHunger = isExtremeHunger;
        this.isSuddenVisionChanges = isSuddenVisionChanges;
        this.isTinglingOrNumbnessInHandsOrFeet = isTinglingOrNumbnessInHandsOrFeet;
        this.isVeryDrySkin = isVeryDrySkin;
        this.isSoresThatAreSlowToHeal = isSoresThatAreSlowToHeal;
        this.isMoreInfectionsThanUsual = isMoreInfectionsThanUsual;
    };

    public Questionnaire(){};

    public UUID getUid() {
        return uid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getIsFrequentUrination() {
        return isFrequentUrination;
    }

    public void setIsFrequentUrination(Boolean isFrequentUrination) {
        this.isFrequentUrination = isFrequentUrination;
    }

    public Boolean getIsExcessiveThirst(){
        return isExcessiveThirst;
    }

    public void setIsExcessiveThirst(Boolean isExcessiveThirst) {
        this.isExcessiveThirst = isExcessiveThirst;
    }

    public Boolean getIsUnexplainedWeightLoss(){
        return isUnexplainedWeightLoss;
    }

    public void setIsSuddenVisionChanges(Boolean isSuddenVisionChanges){
        this.isSuddenVisionChanges = isSuddenVisionChanges;
    }

    public Boolean getIsTinglingOrNumbnessInHandsOrFeet(){
        return isTinglingOrNumbnessInHandsOrFeet;
    }

    public void setIsTinglingOrNumbnessInHandsOrFeet(Boolean isTinglingOrNumbnessInHandsOrFeet){
        this.isTinglingOrNumbnessInHandsOrFeet = isTinglingOrNumbnessInHandsOrFeet;
    }

    public Boolean getIsVeryDrySkin(){
        return isVeryDrySkin;
    }

    public void setIsSoresThatAreSlowToHeal(Boolean isSoresThatAreSlowToHeal){
        this.isSoresThatAreSlowToHeal = isSoresThatAreSlowToHeal;
    }

    public Boolean getIsMoreInfectionsThanUsual(){
        return isMoreInfectionsThanUsual;
    }

    public void setIsMoreInfectionsThanUsual(Boolean isMoreInfectionsThanUsual){
        this.isMoreInfectionsThanUsual = isMoreInfectionsThanUsual;
    }

}



