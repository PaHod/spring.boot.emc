package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    void save(Patient patient);

    List<Patient> findAll();

    Optional<Patient> findById(int id);

    Patient findByIdOrThrow(int id);

    void update(Patient patient);

    void deleteById(int id);
}
