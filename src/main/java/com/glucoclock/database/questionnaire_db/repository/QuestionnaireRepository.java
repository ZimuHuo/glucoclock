package com.glucoclock.database.questionnaire_db.repository;

import com.glucoclock.database.questionnaire_db.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;


public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
    <S extends Questionnaire> S save(S s);
}
