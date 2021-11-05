package com.spring.boot.emc.controller;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.MedicalDocument;
import com.spring.boot.emc.entity.MedicalTool;
import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.service.DoctorService;
import com.spring.boot.emc.service.MyService;
import com.spring.boot.emc.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emc")
public class EmcRestController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    private final MyService myService;

    public EmcRestController(PatientService patientService, DoctorService doctorService, MyService myService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.myService = myService;
    }

    @PostMapping("/patients/add")
    public void addPatient(@RequestBody Patient patient) {
        patientService.save(patient);
    }

    @PostMapping("/patients/add/all")
    public void addAllPatients(@RequestBody List<Patient> patients) {
        patientService.saveAll(patients);
    }

    @GetMapping("/patients/show/all")
    public List<Patient> showAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/patients/show/{id}")
    public Patient showPatientById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @PutMapping("/patients/update")
    public void updatePatient(@RequestBody Patient patient) {
        patientService.update(patient);
    }

    @DeleteMapping("/patients/delete/{id}")
    public void removePatientById(@PathVariable Integer id) {
        patientService.deleteById(id);
    }

    @DeleteMapping("/patients/delete")
    public void removePatientByEntity(@RequestBody Patient patient) {
        patientService.deleteByEntity(patient);
    }


    @PostMapping("/doctors/add")
    public void addDoctor(@RequestBody Doctor doctor) {
        doctorService.save(doctor);
    }

    @GetMapping("/doctors/show/all")
    public List<Doctor> showAllDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/doctors/show/{id}")
    public Doctor showDoctorById(@PathVariable Integer id) {
        return doctorService.findById(id);
    }

    @GetMapping("/doctors/show/ByFullName")
    public Doctor showDoctorByFirstAndLastNames(@RequestBody Doctor doctor) {
        return doctorService.findByFirstAndLastNames(doctor);
    }

    @GetMapping("/doctors/show/PatientsByDoctorId/{id}")
    public List<Patient> showPatientsByDoctorId(@PathVariable Integer id) {
        Doctor doctor = doctorService.findById(id);
        return doctor.getPatients();
    }

    @PutMapping("/doctors/update")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @DeleteMapping("/doctors/delete/{id}")
    public void removeDoctorById(@PathVariable int id) {
        doctorService.removeById(id);
    }

    @DeleteMapping("/doctors/delete")
    public void removeDoctorByEntity(@RequestBody() Doctor doctor) {
        doctorService.remove(doctor);
    }

    @PostMapping("/doctors/addPatientToDoctor/{doctorId}")
    public void addPatientToDoctor(@PathVariable Integer doctorId, @RequestBody Patient patient) {
        Doctor doctor = doctorService.findById(doctorId);
        doctor.addPatientToDoctor(patient);
        doctorService.update(doctor);
    }

    @PostMapping("/doctors/addAllMedicalToolsToDoctor/{doctorId}")
    public void addAllMedicalToolsToDoctor(@PathVariable Integer doctorId, @RequestBody List<MedicalTool> medicalTools) {
        Doctor doctor = doctorService.findById(doctorId);
        for (MedicalTool medicalTool : medicalTools) {
            doctor.addMedicalToolToDoctor(medicalTool);
        }
        doctorService.update(doctor);
    }

    @PostMapping("/doctors/addMedicalDocumentsToDoctor/{doctorId}")
    public void addMedicalDocumentsToDoctor(@PathVariable Integer doctorId, @RequestBody List<MedicalDocument> medicalDocuments) {
        Doctor doctor = doctorService.findById(doctorId);
        for (MedicalDocument medicalDocument : medicalDocuments) {
            doctor.addMedicalDocumentToDoctor(medicalDocument);
        }
        doctorService.update(doctor);
    }

    @GetMapping("/doctors/showPatientsByDoctorIdEntryManager/{id}")
    public List<Patient> showPatientsByDoctorIdEntryManager(@PathVariable Integer id) {
        return myService.findPatientsByDoctorId(id);
    }

    @PostMapping("/patients/addPatientEntityManager")
    public void addPatientEntityManager(@RequestBody Patient patient) {
        myService.savePatient(patient);
    }
}
