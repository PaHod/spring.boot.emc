package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Optional<Doctor> findById(int id);

    Doctor findByIdOrThrow(int doctorId);

    List<Doctor> findAll();

    Doctor update(Doctor doctor);

    void removeById(int id);
}
