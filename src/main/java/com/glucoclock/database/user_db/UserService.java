package com.glucoclock.database.user_db;

import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private BCryptPasswordEncoder encoder;

    @Autowired private UserRepository userRepo;

    public UserRepository getRepository(){
        return userRepo;
    }

    public void save (User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
