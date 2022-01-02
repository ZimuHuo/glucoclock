package com.glucoclock.security.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User getUserByUid(UUID uid);


}