package com.spring.boot.emc.service;

import com.spring.boot.emc.model.Patient;

import java.util.List;

public interface IPatientService {

    Patient save(Patient patient);

    Patient update(Patient patient);

    List<Patient> findAll();

    Patient findByIdOrThrow(int id);

    void removeById(int id);
}
