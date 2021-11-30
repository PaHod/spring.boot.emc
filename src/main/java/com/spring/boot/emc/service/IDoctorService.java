package com.spring.boot.emc.service;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.model.Doctor;

import java.util.List;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor findByIdOrThrow(int doctorId);

    List<Doctor> findAll();

    Doctor update(Doctor doctor);

    void removeById(int id);
}
