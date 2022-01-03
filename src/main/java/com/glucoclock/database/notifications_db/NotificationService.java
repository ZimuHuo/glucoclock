package com.glucoclock.database.notifications_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository repository;

//    Get repository
    public NotificationRepository getRepository() {
        return repository;
    }


//    Change the status of request
    public void resolveRequest(long id) {
        Notification n = repository.getNotificationById(id);
        n.setStatus("Resolved");
        repository.save(n);
    }



}
