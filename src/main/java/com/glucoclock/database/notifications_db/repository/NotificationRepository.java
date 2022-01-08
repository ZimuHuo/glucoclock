package com.glucoclock.database.notifications_db.repository;

import com.glucoclock.database.notifications_db.model.Notifications;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends CrudRepository<Notifications, Long> {

//    Search for a patient's all notifications
    List<Notifications> getNotificationByPatientuid(UUID uid);

//    Search for a doctor's all notifications
    List<Notifications> getNotificationByDoctoruid(UUID uid);

    List<Notifications> getNotificationsByPatientuidAndStatus(UUID uid, String status);

    List<Notifications> getNotificationsByDoctoruidAndStatus(UUID uid, String status);

//    Search for a single notification
    Notifications getNotificationById(long id);

    @Override
    <S extends Notifications> S save(S s);
}
