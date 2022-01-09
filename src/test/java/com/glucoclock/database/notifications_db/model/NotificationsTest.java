package com.glucoclock.database.notifications_db.model;

import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.patients_db.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationsTest {

    private Notifications n = new Notifications();
    LocalDateTime now = LocalDateTime.now();



    @Test
    void getPatientFirstName() {
        assertEquals("fn",n.getPatientFirstName());
    }

    @Test
    void getPatientLastName() {
        assertEquals("ln",n.getPatientLastName());
    }

    @Test
    void getDate() {
        assertEquals(now,n.getDate());
    }

    @Test
    void getRequestType() {
        assertEquals("1",n.getRequestType());
    }

    @Test
    void getStatus() {
        assertEquals("s",n.getStatus());
    }

    @Test
    void getShortMessage() {
        assertEquals("sss",n.getShortMessage());
    }

    @Test
    void getCompleteMessage() {
        assertEquals("ccc",n.getCompleteMessage());
    }

    @Test
    void getReplymessage() {
        assertEquals("rrr",n.getReplymessage());
    }





    @Test
    void setPatientFirstName() {
        n.setPatientFirstName("fn");
        assertEquals("fn",n.getPatientFirstName());
    }

    @Test
    void setPatientLastName() {
        n.setPatientLastName("ln");
        assertEquals("ln",n.getPatientLastName());
    }

    @Test
    void setDate() {
        n.setDate(now);
        assertEquals(now,n.getDate());
    }

    @Test
    void setRequestType() {
        n.setRequestType("1");
        assertEquals("1",n.getRequestType());
    }

    @Test
    void setStatus() {
        n.setStatus("s");
        assertEquals("s",n.getStatus());
    }

    @Test
    void setShortMessage() {
        n.setShortMessage("sss");
        assertEquals("sss",n.getShortMessage());
    }

    @Test
    void setCompleteMessage() {
        n.setCompleteMessage("ccc");
        assertEquals("ccc",n.getCompleteMessage());
    }

    @Test
    void setReplymessage() {
        n.setReplymessage("rrr");
        assertEquals("rrr",n.getReplymessage());
    }



}