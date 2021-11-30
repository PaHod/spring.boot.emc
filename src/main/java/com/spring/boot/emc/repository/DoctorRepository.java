package com.spring.boot.emc.repository;

import com.spring.boot.emc.model.Doctor;

import java.util.Optional;

public interface DoctorRepository {

    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor);

    Optional<Doctor> findById(int id);

    Iterable<Doctor> findAll();

    void deleteById(int id);
}
