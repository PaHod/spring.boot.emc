package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Patient;

import java.util.List;

public interface MyService {

    Patient savePatient(Patient patient);

    List<Patient> findPatientsByDoctorId(Integer id);

}
