package org.carpio.com.demoapp4.repository;

import org.carpio.com.demoapp4.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByGenderEquals(String gender);
    List<Patient> findByGenderAndAgeGreaterThan(String gender, Integer ageIsGreaterThan);

    List<Patient> findByAgeLessThan(Integer ageIsLessThan);
    List<Patient> findByDocument(String document);
}
