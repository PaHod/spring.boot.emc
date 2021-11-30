package com.spring.boot.emc.repository;

import com.spring.boot.emc.model.Patient;

import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);

    Patient update(Patient patient);

    Optional<Patient> findById(int id);

    Iterable<Patient> findAll();

    void deleteById(int id);
}
