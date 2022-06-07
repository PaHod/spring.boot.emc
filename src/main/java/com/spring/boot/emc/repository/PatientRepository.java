package com.spring.boot.emc.repository;

import com.spring.boot.emc.model.Patient;

import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);

    Optional<Patient> findById(Integer id);

    Iterable<Patient> findAll();

    void deleteById(Integer id);
}
