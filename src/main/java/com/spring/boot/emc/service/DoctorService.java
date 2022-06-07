package com.spring.boot.emc.service;

import com.spring.boot.emc.exception.DoctorNotFoundException;
import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.repository.DoctorRepository;
import com.spring.boot.emc.welcome.WelcomeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final WelcomeService welcomeService;

    public DoctorService(DoctorRepository doctorRepository,
                         WelcomeService welcomeService) {
        this.doctorRepository = doctorRepository;
        this.welcomeService = welcomeService;
    }

    @Override
    public Doctor save(Doctor doctor) {
        //verify data
        Doctor savedDoctor = doctorRepository.save(doctor);

        welcomeService.sendGreetingsToNewDoctor(savedDoctor);
        return savedDoctor;
    }

    @Override
    public Doctor findByIdOrThrow(int id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @Override
    public List<Doctor> findAll() {
        Iterable<Doctor> all = doctorRepository.findAll();
        ArrayList<Doctor> doctors = new ArrayList<>();
        all.forEach(doctors::add);
        return doctors;
    }

    @Override
    public Doctor update(Doctor doctor) {
//        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public void removeById(int id) {
        doctorRepository.deleteById(id);
    }
}
