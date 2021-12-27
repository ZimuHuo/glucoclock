package com.glucoclock.database.user_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {
    @Autowired private BCryptPasswordEncoder encoder;

    @Autowired private UserRepository userRepo;

    public void save (User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
