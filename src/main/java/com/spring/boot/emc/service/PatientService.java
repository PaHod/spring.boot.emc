package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Patient;

import java.util.List;

public interface PatientService {

    void save(Patient patient);

    void saveAll(List<Patient> patients);

    List<Patient> findAll();

    Patient findById(Integer id);

    void update(Patient patient);

    void deleteById(Integer id);

    void deleteByEntity(Patient patient);
}
