package com.glucoclock.database.user_db;

import com.glucoclock.database.patients_db.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByEmail(String email);
    @Override
    <S extends User> S save(S s);
}
