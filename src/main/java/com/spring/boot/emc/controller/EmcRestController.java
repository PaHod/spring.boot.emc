package com.spring.boot.emc.controller;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.service.DoctorService;
import com.spring.boot.emc.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emc")
public class EmcRestController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    public EmcRestController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
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
        doctorService.saveDoctor(doctor);
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
        return doctorService.findDoctorByFirstAndLastNames(doctor);
    }

    @PutMapping("/doctors/update")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/doctors/delete/{id}")
    public void removeDoctorById(@PathVariable int id) {
        doctorService.removeDoctorById(id);
    }

    @DeleteMapping("/doctors/delete")
    public void removeDoctorByEntity(@RequestBody() Doctor doctor) {
        doctorService.removeDoctor(doctor);
    }
}
