package com.glucoclock.database.questionnaire_db.service;

import com.glucoclock.database.questionnaire_db.repository.QuestionnaireRepository;
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
