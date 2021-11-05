package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.repository.IDoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor findById(Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findByFirstAndLastNames(Doctor doctor) {
        return doctorRepository.findByFirstNameAndLastName(doctor.getFirstName(), doctor.getLastName());
    }

    @Override
    public Doctor update(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void removeById(Integer id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public void remove(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
