package com.glucoclock.security.db;


import com.glucoclock.database.doctors_db.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class UserService extends CrudService<User, Integer> {

    private UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRepository getRepository() {
        return repository;
    }

    public void updateUserPassword(String username, String newpassword) {
        User user  = repository.findByUsername(username);
        user.setPassword(newpassword);
        repository.save(user);
    }


}