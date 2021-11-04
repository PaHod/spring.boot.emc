package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void saveDoctor(Doctor doctor);

    Doctor findById(Integer id);

    List<Doctor> findAll();

    Doctor findDoctorByFirstAndLastNames(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    void removeDoctorById(Integer id);

    void removeDoctor(Doctor doctor);
}
