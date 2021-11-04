package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.repository.IDoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void saveDoctor(Doctor doctor) {
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
    public Doctor findDoctorByFirstAndLastNames(Doctor doctor) {
        return doctorRepository.findByFirstNameAndLastName(doctor.getFirstName(), doctor.getLastName());
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void removeDoctorById(Integer id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public void removeDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
