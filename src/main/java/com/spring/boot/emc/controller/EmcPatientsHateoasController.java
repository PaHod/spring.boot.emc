package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorSimpleDTO;
import com.spring.boot.emc.dto.PatientDTO;
import com.spring.boot.emc.dto.PatientSimpleDTO;
import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.mapper.DoctorMapper;
import com.spring.boot.emc.mapper.PatientMapper;
import com.spring.boot.emc.service.IDoctorService;
import com.spring.boot.emc.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/emc/patients")
public class EmcPatientsHateoasController {

    private final IPatientService patientService;
    private final IDoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final ModelHelper modelHelper;

    @Autowired
    public EmcPatientsHateoasController(IPatientService patientService, IDoctorService doctorService, DoctorMapper doctorMapper, PatientMapper patientMapper, ModelHelper modelHelper) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
        this.modelHelper = modelHelper;
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientMapper.toNewEntity(patientDTO);
        patientService.save(patient);

        URI location = linkTo(methodOn(EmcPatientsHateoasController.class).getPatient(patient.getId()))
                .withSelfRel()
                .toUri();
        EntityModel<PatientDTO> model = modelHelper.toModelWithLinksPatient(patientMapper.toDTO(patient));
        return ResponseEntity.created(location).body(model);
    }

    @GetMapping
    public ResponseEntity<?> allPatients() {
        List<EntityModel<PatientSimpleDTO>> collect = patientService.findAll()
                .stream()
                .map(patientMapper::toSimpleDTO)
                .map(modelHelper::toModelWithLinksPatientSimple)
                .collect(toList());

        return ResponseEntity.ok(CollectionModel.of(collect));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable int id) {
        Optional<Patient> patientOpt = patientService.findById(id);
        if (patientOpt.isEmpty()) {
            return patientNotFountResponse(id);
        }
        return ResponseEntity.ok(modelHelper.toModelWithLinksPatient(patientMapper.toDTO(patientOpt.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") int id, @RequestBody PatientDTO patientDTO) {
        Optional<Patient> patientOpt = patientService.findById(id);
        if (patientOpt.isEmpty()) {
            return patientNotFountResponse(id);
        }
        Patient patient = patientMapper.updateEntityFromDTO(patientOpt.get(), patientDTO);
        patientService.update(patient);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePatient(@PathVariable("id") int id) {
        try {
            patientService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/doctors")
    public ResponseEntity<?> getPatientsDoctors(@PathVariable int id) {
        Optional<Patient> patientOpt = patientService.findById(id);
        if (patientOpt.isEmpty() || patientOpt.get().getDoctors() == null) {
            return patientNotFountResponse(id);
        }
        List<EntityModel<DoctorSimpleDTO>> doctorsModels = patientOpt.get().getDoctors()
                .stream()
                .map(doctorMapper::toSimpleDTO)
                .map(modelHelper::toModelWithLinksDoctor)
                .collect(toList());
        return ResponseEntity.ok(modelHelper.toCollectionModelWithDoctorsLink(doctorsModels, id));
    }

    @PutMapping("/{patientId}/doctors/{doctorId}")
    public ResponseEntity<?> patientAddDoctor(@PathVariable int patientId, @PathVariable int doctorId) {
        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            return patientNotFountResponse(patientId);
        }

        Optional<Doctor> doctorOpt = doctorService.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            return doctorNotFoundResponse(doctorId);
        }

        Patient patient = patientOpt.get();
        patient.addDoctor(doctorOpt.get());

        patientService.update(patient);

        return ResponseEntity.ok(CollectionModel.of(patient.getDoctors()
                .stream()
                .map(doctorMapper::toSimpleDTO)
                .map(modelHelper::toModelWithLinksDoctor)
                .collect(toList())));
    }

    @DeleteMapping("/{patientId}/doctors/{doctorId}")
    public ResponseEntity<?> removeDoctorFromPatient(@PathVariable int patientId, @PathVariable int doctorId) {
        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            return patientNotFountResponse(patientId);
        }
        Optional<Doctor> doctorOpt = doctorService.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            return doctorNotFoundResponse(doctorId);
        }

        Patient patient = patientOpt.get();
        boolean isRemoved = patient.getDoctors().removeIf(d -> d.getId() == doctorId);
        if (!isRemoved) {
            String body = String.format("Patient with id=%d dont have a doctor with id=%d", patientId, doctorId);
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(body);
        }
        patientService.update(patient);
        return ResponseEntity.ok(CollectionModel.of(patient.getDoctors()
                .stream()
                .map(doctorMapper::toSimpleDTO)
                .map(modelHelper::toModelWithLinksDoctor)
                .collect(toList())));

    }

    private ResponseEntity<String> doctorNotFoundResponse(@PathVariable int doctorId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found doctor with id=" + doctorId);
    }

    private ResponseEntity<String> patientNotFountResponse(@PathVariable int patientId) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found patient with id=" + patientId);
    }
}
