package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.dto.mapper.DoctorMapper;
import com.spring.boot.emc.dto.mapper.PatientMapper;
import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.service.IDoctorService;
import com.spring.boot.emc.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final IPatientService patientService;
    private final IDoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    @Autowired
    public DoctorController(IPatientService patientService,
                            IDoctorService doctorService,
                            DoctorMapper doctorMapper,
                            PatientMapper patientMapper) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ResponseBody
    public DoctorDTO addDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toNewEntity(doctorDTO);
        Doctor savedDoctor = doctorService.save(doctor);
        return doctorMapper.toDTO(savedDoctor);
    }

    @GetMapping
    @ResponseBody
    public List<DoctorDTO> allDoctors() {
        return doctorService.findAll()
                .stream()
                .map(doctorMapper::toDtoMainFields)
                .collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DoctorDTO getDoctor(@PathVariable("id") int id) {
        Doctor doctor = doctorService.findByIdOrThrow(id);
        return doctorMapper.toDTO(doctor);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public DoctorDTO updateDoctor(@PathVariable("id") int id, @RequestBody DoctorDTO doctorDTO) {
        Doctor doctorDyId = doctorService.findByIdOrThrow(id);
        Doctor doctor = doctorMapper.updateEntityFromDTO(doctorDyId, doctorDTO);
        doctorService.update(doctor);
        return doctorMapper.toDTO(doctor);
    }

//    @DeleteMapping("/{id}")
//    public void removeDoctor(@PathVariable("id") int id) {
//        doctorService.removeById(id);
//    }
//
//    @GetMapping("/{id}/patients")
//    @ResponseBody
//    public List<PatientDTO> showAllPatientsOfDoctorById(@PathVariable("id") int id) {
//        Doctor doctor = doctorService.findByIdOrThrow(id);
//        return doctor.getPatients()
//                .stream()
//                .map(patientMapper::toDtoMainFields)
//                .collect(toList());
//    }

//    @PostMapping("/{doctorId}/patients/{patientId}")
//    @ResponseBody
//    public List<PatientDTO> addPatientToDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
//        Doctor doctor = doctorService.findByIdOrThrow(doctorId);
//        Patient patient = patientService.findByIdOrThrow(patientId);
//
//        doctor.addPatient(patient);
//        doctorService.update(doctor);
//
//        return doctor.getPatients()
//                .stream()
//                .map(patientMapper::toDtoNoLists)
//                .collect(toList());
//    }

    //todo replace with Appointments
//    @DeleteMapping("/{doctorId}/patients/{patientId}")
//    @ResponseBody
//    public List<PatientDTO> removePatientFromDoctor(@PathVariable int doctorId, @PathVariable int patientId) {
//        Doctor doctor = doctorService.findByIdOrThrow(doctorId);
//        Patient patient = patientService.findByIdOrThrow(patientId);
//
//        doctor.addPatient(patient);
//        boolean isRemoved = doctor.getPatients().removeIf(p -> p.getId() == patientId);
//        if (!isRemoved) {
//            throw new CannotRemoveEntityException(doctorId, patientId);
//        }
//        doctorService.create(doctor);
//        return doctor.getPatients()
//                .stream()
//                .map(patientMapper::toDtoNoLists)
//                .collect(toList());
//    }
}
