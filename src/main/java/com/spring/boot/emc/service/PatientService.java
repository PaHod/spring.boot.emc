package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.exception.PatientNotFoundException;
import com.spring.boot.emc.repository.IPatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void save(Patient patient) {
        Objects.requireNonNull(patient);
        patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(int id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }
}
