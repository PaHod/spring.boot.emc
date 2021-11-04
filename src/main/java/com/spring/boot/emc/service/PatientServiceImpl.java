package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Patient;
import com.spring.boot.emc.repository.IPatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final IPatientRepository patientRepository;

    public PatientServiceImpl(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void saveAll(List<Patient> patients) {
        for (Patient patient : patients) {
            patientRepository.save(patient);
        }
    }

    @Override
    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElse(new Patient());
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(Integer id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void deleteByEntity(Patient patient) {
        patientRepository.delete(patient);
    }
}
