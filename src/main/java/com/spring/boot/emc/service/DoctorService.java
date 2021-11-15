package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.exception.DoctorNotFoundException;
import com.spring.boot.emc.repository.IDoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> findById(int id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void removeById(int id) {
        doctorRepository.deleteById(id);
    }
}
