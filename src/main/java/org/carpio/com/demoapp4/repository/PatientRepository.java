package org.carpio.com.demoapp4.repository;

import org.carpio.com.demoapp4.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByGenderEquals(String gender);
    List<Patient> findByGenderAndAgeGreaterThan(String gender, Integer ageIsGreaterThan);

    List<Patient> findByAgeLessThan(Integer ageIsLessThan);
    List<Patient> findByDocument(String document);

    @Query("SELECT p FROM Patient p WHERE LOWER(p.fullName) like lower(concat(:fullName, '%'))")
    List<Patient> findAllPatientsByFullNamePersonalizado(@Param("fullName") String fullName);
}
