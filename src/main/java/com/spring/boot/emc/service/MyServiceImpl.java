package com.spring.boot.emc.service;

import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.Patient;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * main reason to try own service implementation using EntityManager
 */
@Service
public class MyServiceImpl implements MyService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Patient savePatient(Patient patient) {
        em.persist(patient);
        return patient;
    }

    @Transactional
    public List<Patient> findPatientsByDoctorId(Integer id) {
        Doctor doctor = em.find(Doctor.class, id);

        System.out.println("doctor:");
        System.out.println(doctor);
        System.out.println("documents:");
        System.out.println(doctor.getMedicalDocuments());
        System.out.println("tools:");
        System.out.println(doctor.getMedicalTools());
        System.out.println("patients:");
        System.out.println(doctor.getPatients());

        return doctor.getPatients();
    }
}
