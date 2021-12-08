package com.glucoclock.database.comprehensiveLogBook_db.repository;
import com.glucoclock.database.comprehensiveLogBook_db.model.ComprehensiveLogBook;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ComprehensiveLogBookRepository extends CrudRepository<ComprehensiveLogBook, Long>{
    List<ComprehensiveLogBook> findAll();
    List<ComprehensiveLogBook> findByPatientid(Long patient_id);
    ComprehensiveLogBook findByPatientidAndTimeAndDate(Long patient_id,String Time, LocalDate Date);
    List<ComprehensiveLogBook> findByPatientidAndAndDate(Long patient_id,LocalDate Date);
    @Override
    <S extends ComprehensiveLogBook> S save(S s);

}
