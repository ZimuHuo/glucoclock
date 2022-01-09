package com.glucoclock.database.notifications_db.service;

import com.glucoclock.database.notifications_db.model.Notifications;
import com.glucoclock.database.notifications_db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    public Integer countUnresolvedMsg(UUID uid, String role){
        int unresolvedMsgN = 0;
        if(role.equals("PATIENT")){
            unresolvedMsgN = repository.getNotificationsByPatientuidAndRequesttypeAndStatus(uid,"Add Patient Request","Unresolved").size();
        }else if(role.equals("DOCTOR")){
            unresolvedMsgN =
                    repository.getNotificationsByDoctoruidAndRequesttypeAndStatus(uid,"Blood Glucose Alarm","Unresolved").size()
            +repository.getNotificationsByDoctoruidAndRequesttypeAndStatus(uid,"Questionnaire","Unresolved").size();
        }
        return unresolvedMsgN;
    }

    public List<Notifications> getNotificationlist(UUID doctorUid){
        List<Notifications> returnNotificationlist;
        returnNotificationlist=repository.getNotificationByDoctoruid(doctorUid);
        return returnNotificationlist;
    }

}
