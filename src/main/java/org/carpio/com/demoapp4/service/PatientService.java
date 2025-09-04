package org.carpio.com.demoapp4.service;

import org.carpio.com.demoapp4.model.Patient;
import org.carpio.com.demoapp4.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PatientService {

    Logger log = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        log.info("Fetching all patients from the database");
        return patientRepository.findAll();
    }
    public void savePatient(Patient patient){
        log.info("Saving new patient: {}", patient.getFullName());
        patientRepository.save(patient);
    }

}
