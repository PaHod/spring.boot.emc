package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.dto.PatientDTO;
import com.spring.boot.emc.dto.mapper.DoctorMapper;
import com.spring.boot.emc.dto.mapper.PatientMapper;
import com.spring.boot.emc.model.Patient;
import com.spring.boot.emc.service.IDoctorService;
import com.spring.boot.emc.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/patients")
public class PatientsController {

    private final IPatientService patientService;
    private final IDoctorService doctorService;

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientsController(IPatientService patientService,
                              IDoctorService doctorService,
                              DoctorMapper doctorMapper,
                              PatientMapper patientMapper
    ) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ResponseBody
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientMapper.toNewEntity(patientDTO);
        return patientMapper.toDTO(patientService.save(patient));
    }

    @GetMapping
    @ResponseBody
    public List<PatientDTO> allPatients() {
        return patientService.findAll()
                .stream()
                .map(patientMapper::toDtoMainFields)
                .collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PatientDTO getPatient(@PathVariable int id) {
        Patient patient = patientService.findByIdOrThrow(id);
        return patientMapper.toDTO(patient);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PatientDTO updatePatient(@PathVariable("id") int id, @RequestBody PatientDTO patientDTO) {
        Patient oldPatient = patientService.findByIdOrThrow(id);

        Patient patient = patientMapper.updateEntityFromDTO(oldPatient, patientDTO);
        return patientMapper.toDTO(patientService.update(patient));
    }

    @DeleteMapping("/{id}")
    public void removePatient(@PathVariable("id") int id) {
        patientService.removeById(id);
    }

    @GetMapping("/{id}/doctors")
    public List<DoctorDTO> getPatientsDoctors(@PathVariable int id) {
        Patient patient = patientService.findByIdOrThrow(id);
        return ofNullable(patient.getDoctors())
                .orElse(Collections.emptyList())
                .stream()
                .map(doctorMapper::toDtoMainFields)
                .collect(toList());
    }

    //todo replace with Appointments

//    @PostMapping("/{patientId}/doctors/{doctorId}")
//    public ResponseEntity<?> patientAddDoctor(@PathVariable int patientId, @PathVariable int doctorId) {
//        Optional<Patient> patientOpt = patientService.findById(patientId);
//        if (patientOpt.isEmpty()) {
//            return patientNotFountResponse(patientId);
//        }
//
//        Optional<Doctor> doctorOpt = doctorService.findById(doctorId);
//        if (doctorOpt.isEmpty()) {
//            return doctorNotFoundResponse(doctorId);
//        }
//
//        Patient patient = patientOpt.get();
//        patient.addDoctor(doctorOpt.get());
//
//        patientService.update(patient);
//        return ResponseEntity.ok(CollectionModel.of(patient.getDoctors()
//                .stream()
//                .map(doctorMapper::toDtoNoLists)
//                .collect(toList())));
//    }
//
//    @DeleteMapping("/{patientId}/doctors/{doctorId}")
//    public ResponseEntity<?> removeDoctorFromPatient(@PathVariable int patientId, @PathVariable int doctorId) {
//        Optional<Patient> patientOpt = patientService.findById(patientId);
//        if (patientOpt.isEmpty()) {
//            return patientNotFountResponse(patientId);
//        }
//        Optional<Doctor> doctorOpt = doctorService.findById(doctorId);
//        if (doctorOpt.isEmpty()) {
//            return doctorNotFoundResponse(doctorId);
//        }
//
//        Patient patient = patientOpt.get();
//        boolean isRemoved = patient.getDoctors().removeIf(d -> d.getId() == doctorId);
//        if (!isRemoved) {
//            String body = String.format("Patient with id=%d dont have a doctor with id=%d", patientId, doctorId);
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(body);
//        }
//        patientService.update(patient);
//        return ResponseEntity.ok(CollectionModel.of(patient.getDoctors()
//                .stream()
//                .map(doctorMapper::toDtoNoLists)
//                .collect(toList())));
//    }
//
//    private ResponseEntity<String> doctorNotFoundResponse(@PathVariable int doctorId) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found doctor with id=" + doctorId);
//    }
//
//    private ResponseEntity<String> patientNotFountResponse(@PathVariable int patientId) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found patient with id=" + patientId);
//    }
}
