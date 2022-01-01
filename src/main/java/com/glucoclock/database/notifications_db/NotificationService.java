package com.glucoclock.database.notifications_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository repository;


//    Change the status of request
    public void updateStatus(long id, String status) {
        Notification n = repository.getNotificationByID(id);
        n.setStatus(status);
        repository.save(n);
    }

}
