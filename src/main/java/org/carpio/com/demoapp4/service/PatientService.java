package org.carpio.com.demoapp4.service;

import org.carpio.com.demoapp4.dto.PatientDto;
import org.carpio.com.demoapp4.model.Patient;
import org.carpio.com.demoapp4.repository.PatientRepository;
import org.carpio.com.demoapp4.util.PatientMapper;
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

    @Autowired
    private PatientMapper patientMapper;

    public List<Patient> getAllPatients(){
        log.info("Fetching all patients from the database");
        return patientRepository.findAll();
    }
    public Patient getPatientByNro(String nro){
        log.info("Fetching patient with Numero de documento: {}", nro);
        Patient newPatient = new Patient();
        List<Patient> patients = patientRepository.findAll();
        for(Patient patient : patients){
            if(patient.getDocument().equals(nro)){
                newPatient = patient;
            }
        }
        return newPatient;
    }
    public List<Patient> getPatientsByGenderAndAge(String gender, Integer age){
        log.info("Fetching patients with gender selected: {} and age: {}", gender, age);
        return patientRepository.findAll().stream()
                .filter(p -> p.getGender().equals(gender) && p.getAge() >age)
                .toList();
    }
    public List<Patient> getPatientsByGenderAndAgeV2(String gender, Integer age){
        log.info("Fetching patients with gender v2 selected: {} and age: {}", gender, age);
        return patientRepository.findByGenderAndAgeGreaterThan(gender, age);
    }
    public List<Patient> getPatientsByAgeLessThan(Integer age){
        log.info("Fetching patients younger than: {}", age);
        return patientRepository.findByAgeLessThan(age);
    }
    public List<Patient> getPatientsByDocument(String document){
        log.info("Fetching patients with document: {}", document);
        return patientRepository.findByDocument(document);
    }

    public List<Patient> getAllPatientsByAge(Integer age){
        log.info("Fetching patients older than: {}", age);
        return patientRepository.findAll().stream()
                .filter(p -> p.getAge() > age)
                .toList();
    }
    public List<Patient> getPatientsByGender(String gender){
        log.info("Fetching patients with gender selected: {}", gender);
        return patientRepository.findByGenderEquals(gender);
    }
    public void savePatient(Patient patient){
        log.info("Saving new patient: {}", patient.getFullName());
        patientRepository.save(patient);
    }
    public Patient savePatient2(Patient patient){
        log.info("Saving new patient v2: {}", patient.getFullName());
        return patientRepository.save(patient);
    }
    public PatientDto savePatient3(PatientDto patient){
        log.info("Saving new patient v3: {}", patient.getNombres());
        Patient patientNew = patientRepository.save(patientMapper.patientDtoToPatient(patient));
        return patientMapper.patientToPatientDto(patientNew);
    }
    public Patient updatePatient(Long id, Patient patient){
        log.info("Updating patient: {}", patient.getFullName());
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        existingPatient.setFullName(patient.getFullName());
        existingPatient.setDocument(patient.getDocument());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        return patientRepository.save(existingPatient);
    }
    public void deletePatient(Long id){
        //Validar si existe
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patientRepository.delete(existingPatient);
    }
    public List<Patient> getPatientsByFullName(String fullName){
        log.info("Fetching patients with full name like: {}", fullName);
        return patientRepository.findAllPatientsByFullNamePersonalizado(fullName);
    }

}
