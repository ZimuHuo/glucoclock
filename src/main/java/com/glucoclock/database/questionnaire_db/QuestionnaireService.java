package com.glucoclock.database.questionnaire_db;

import com.glucoclock.database.patients_db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {
    @Autowired
    QuestionnaireRepository repository;

    public QuestionnaireRepository getRepository(){
        return repository;
    }
}
