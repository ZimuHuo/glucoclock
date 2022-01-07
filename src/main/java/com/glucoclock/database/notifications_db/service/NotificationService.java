package com.glucoclock.database.notifications_db.service;

import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.notifications_db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Notifications n = repository.getNotificationById(id);
        n.setStatus("Resolved");
        repository.save(n);
    }

//  Set the reply message
    public void reply(long id, String msg) {
        Notifications n = repository.getNotificationById(id);
        n.setReplymessage(msg);
        repository.save(n);
    }

}
