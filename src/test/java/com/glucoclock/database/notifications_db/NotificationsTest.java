package com.glucoclock.database.notifications_db;

import com.glucoclock.database.notifications_db.model.Notifications;
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

    @BeforeEach
    void createNotificationsTest(){
        n.setCompleteMessage("ccc");
        n.setShortMessage("sss");
        n.setReplymessage("rrr");
        n.setStatus("s");
        n.setDate(now);
        n.setPatientFirstName("fn");
        n.setPatientLastName("ln");
        n.setRequestType("1");

    }


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
}