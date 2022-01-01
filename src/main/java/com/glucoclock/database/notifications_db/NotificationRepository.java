package com.glucoclock.database.notifications_db;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends CrudRepository {

    List<Notification> getNotificationByUID(UUID uid);
    Notification getNotificationByID(long id);

    <S extends Notification> S save(S s);
}
