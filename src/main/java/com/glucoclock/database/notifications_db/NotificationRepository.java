package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> getNotificationByPatientuid(UUID uid);
    List<Notification> getNotificationByDoctoruid(UUID uid);
    Notification getNotificationById(long id);

    @Override
    <S extends Notification> S save(S s);
}
