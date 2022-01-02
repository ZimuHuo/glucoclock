package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

//    Search for a patient's all notifications
    List<Notification> getNotificationByPatientuid(UUID uid);

//    Search for a doctor's all notifications
    List<Notification> getNotificationByDoctoruid(UUID uid);

//    Search for a single notification
    Notification getNotificationById(long id);

    @Override
    <S extends Notification> S save(S s);
}
