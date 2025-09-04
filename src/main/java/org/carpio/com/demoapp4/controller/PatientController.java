package org.carpio.com.demoapp4.controller;

import com.github.javafaker.Faker;
import org.carpio.com.demoapp4.model.Patient;
import org.carpio.com.demoapp4.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Random;

@RestController
@RequestMapping("/patients")
public class PatientController {
    Logger log = LoggerFactory.getLogger(PatientController.class);
    private final Faker faker = new Faker(new Locale("es-PE"));
    private final Random random = new Random();

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<?> getAllPatients(){
        log.info("Received request to fetch all patients");
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    @PostMapping("/fake")
    public Patient createNewPatient() {
        Patient patient = new Patient();
        patient.setFullName(faker.name().fullName());
        patient.setDocument(faker.number().digits(8));
        patient.setAge(random.nextInt(1, 100));
        patient.setGender(random.nextBoolean() ? "M" : "F");
        patientService.savePatient(patient);
        log.info("Created new patient: {}", patient.getFullName());
        return patient;
    }
}
