package com.glucoclock.database.questionnaire_db;

import org.springframework.data.repository.CrudRepository;


public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
    <S extends Questionnaire> S save(S s);
}
