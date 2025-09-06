package org.carpio.com.demoapp4.controller;

import com.github.javafaker.Faker;
import org.carpio.com.demoapp4.dto.PatientDto;
import org.carpio.com.demoapp4.model.Patient;
import org.carpio.com.demoapp4.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/buscar")
    public ResponseEntity<?> getPatientV1(@RequestParam String nro){
        log.info("Received request to fetch patient with ID: {}", nro);
        if(nro.length() > 0){
            return ResponseEntity.ok(patientService.getPatientByNro(nro));
        }
        return null;
    }
    @GetMapping("/buscar2")
    public ResponseEntity<?> getPatientV2(@RequestParam("gender") String gender, @RequestParam Integer age){
        log.info("Received request to fetch patients with");
        if(gender.length() > 0){
            return ResponseEntity.ok(patientService.getPatientsByGenderAndAge(gender, age));
        }
        return null;
    }
    @GetMapping("/buscar3")
    public ResponseEntity<?> getPatientV3(@RequestParam("otrov3") String otrov2){
        log.info("Received request to fetch patient with ID: {}", otrov2);
        if(otrov2.length() > 0){
            return ResponseEntity.ok(patientService.getPatientByNro(otrov2));
        }
        return null;
    }
    @GetMapping("/buscar4/{age}")
    public ResponseEntity<?> getPatientV4(@PathVariable("age") Integer age){
        if(age > 0){
            return ResponseEntity.ok(patientService.getAllPatientsByAge(age));
        }
        return null;
    }
    @GetMapping("/buscar5/{gender}/{age}")
    public ResponseEntity<?> getPatientV5(@PathVariable("gender") String gender, @PathVariable("age") Integer age){
        if(age > 0){
            return ResponseEntity.ok(patientService.getPatientsByGenderAndAgeV2(gender, age));
        }
        return null;
    }
    @GetMapping("/buscar6/{age}")
    public ResponseEntity<?> getPatientV6(@PathVariable("age") Integer age){
        if(age > 0){
            return ResponseEntity.ok(patientService.getPatientsByAgeLessThan(age));
        }
        return null;
    }
    @GetMapping("/buscarDocument")
    public ResponseEntity<?> getPatientsByDocument(@RequestParam String document){
        log.info("Received request to fetch patients with Document: {}", document);
        return ResponseEntity.ok(patientService.getPatientsByDocument(document));
    }
    @GetMapping("/buscarGender")
    public ResponseEntity<?> getPatientsByGender(@RequestParam String gender){
        log.info("Received request to fetch patients with Gender: {}", gender);
        return ResponseEntity.ok(patientService.getPatientsByGender(gender));
    }
    @PostMapping
    public Patient create(@RequestBody Patient p){
        return patientService.savePatient2(p);
    }
    @PostMapping("/mapstruct")
    public PatientDto createv2(@RequestBody PatientDto p){
        return patientService.savePatient3(p);
    }
    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient p) {
        return patientService.updatePatient(id, p);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
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

    @GetMapping("/cookie")
    public String readCookie(@CookieValue(value = "user-token", defaultValue = "desconocido") String token) {
        log.info("Reading cookie value: {}", token);
        return "Cookie Value: " + token;
    }
    @GetMapping("/multiHeader")
    public String getMultiHeader(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader("Accept-Language") String acceptLanguage) {
        log.info("User-Agent: {}, Accept-Language: {}", userAgent, acceptLanguage);
        return "User-Agent: " + userAgent + ", Accept-Language: " + acceptLanguage;
    }

    @GetMapping("/patients-personalizado")
    public ResponseEntity<?> getPatientsByFullName(@RequestParam String fullName){
        log.info("Received request to fetch patients with full name like: {}", fullName);
        return ResponseEntity.ok(patientService.getPatientsByFullName(fullName));
    }
}
