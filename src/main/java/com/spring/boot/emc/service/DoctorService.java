package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;

import java.util.List;

public interface DoctorService {

    void save(Doctor doctor);

    Doctor findById(Integer id);

    List<Doctor> findAll();

    Doctor findByFirstAndLastNames(Doctor doctor);

    Doctor update(Doctor doctor);

    void removeById(Integer id);

    void remove(Doctor doctor);
}
