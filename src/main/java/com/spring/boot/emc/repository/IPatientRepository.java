package com.spring.boot.emc.repository;

import com.spring.boot.emc.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
}
