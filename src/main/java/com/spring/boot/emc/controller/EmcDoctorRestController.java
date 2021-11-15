package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.dto.DoctorSimpleDTO;
import com.spring.boot.emc.dto.PatientSimpleDTO;
import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.exception.CannotRemoveException;
import com.spring.boot.emc.mapper.DoctorMapper;
import com.spring.boot.emc.mapper.PatientMapper;
import com.spring.boot.emc.service.IDoctorService;
import com.spring.boot.emc.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/emc/doctors")
public class EmcDoctorRestController {

    private final IPatientService patientService;
    private final IDoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    @Autowired
    public EmcDoctorRestController(IPatientService patientService, IDoctorService doctorService, DoctorMapper doctorMapper, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public DoctorDTO addDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.doctorToNewEntity(doctorDTO);
        return doctorMapper.toDTO(doctorService.save(doctor));
    }

    @GetMapping
    public List<DoctorSimpleDTO> allDoctors() {
        return doctorService.findAll()
                .stream()
                .map(doctorMapper::toSimpleDTO)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctor(@PathVariable("id") int id) {
        Doctor doctor = doctorService.findByIdOrThrow(id);
        return doctorMapper.toDTO(doctor);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable("id") int id, @RequestBody DoctorDTO doctorDTO) {
        Doctor doctorDyId = doctorService.findByIdOrThrow(id);
        Doctor doctor = doctorMapper.updateEntityFromDTO(doctorDyId, doctorDTO);
        doctorService.update(doctor);
        return doctorMapper.toDTO(doctor);
    }

    @DeleteMapping("/{id}")
    public void removeDoctor(@PathVariable("id") int id) {
        // TODO: do we need to check if the entity is exist before call .removeById()?
        doctorService.removeById(id);
    }

    @GetMapping("/{id}/patients")
    public List<PatientSimpleDTO> showAllPatientsOfDoctorById(@PathVariable("id") int id) {
        Doctor doctor = doctorService.findByIdOrThrow(id);
        return doctor.getPatients()
                .stream()
                .map(patientMapper::toSimpleDTO)
                .collect(toList());
    }

    @PutMapping("/{doctorId}/patients/{patientId}") // TODO: what is better POST or PUT
    public List<PatientSimpleDTO> addPatientToDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
        Doctor doctor = doctorService.findByIdOrThrow(doctorId);
        Patient patient = patientService.findByIdOrThrow(patientId);

        doctor.addPatientToDoctor(patient);
        doctorService.update(doctor);
        // TODO: for some reason it doesn't update patients list
        return doctor.getPatients()
                .stream()
                .map(patientMapper::toSimpleDTO)
                .collect(toList());
    } // TODO: is it okay that we response with list OR with HttpStatus.500, but with some custom data:
    // "message": "Not found doctor with id=50",
    // "internalCode": 2222

    @DeleteMapping("/{doctorId}/patients/{patientId}")
    public List<PatientSimpleDTO> removePatientFromDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
        Doctor doctor = doctorService.findByIdOrThrow(doctorId);
        Patient patient = patientService.findByIdOrThrow(patientId);

        doctor.addPatientToDoctor(patient);
        boolean isRemoved = doctor.getPatients().removeIf(p -> p.getId() == patientId);
        if (!isRemoved) {
            throw new CannotRemoveException(doctorId, patientId);
        }
        doctorService.save(doctor);
        return doctor.getPatients()
                .stream()
                .map(patientMapper::toSimpleDTO)
                .collect(toList());
    }


}
