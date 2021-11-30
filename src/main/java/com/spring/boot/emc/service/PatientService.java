package com.spring.boot.emc.service;

import com.spring.boot.emc.repository.PatientRepository;
import com.spring.boot.emc.exception.PatientNotFoundException;
import com.spring.boot.emc.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @NonNull
    public Patient save(Patient patient) {
        Objects.requireNonNull(patient);
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient findByIdOrThrow(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public List<Patient> findAll() {
        Iterable<Patient> all = patientRepository.findAll();
        ArrayList<Patient> patients = new ArrayList<>();
        all.forEach(patients::add);
        return patients;
    }

    @Override
    public Patient update(Patient patient) {
        patientRepository.update(patient);
        return patient;
    }

    @Override
    public void removeById(int id) {
        patientRepository.deleteById(id);
    }
}
