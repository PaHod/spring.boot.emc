package com.spring.boot.emc.jpa;

import com.spring.boot.emc.model.Patient;
import com.spring.boot.emc.repository.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Profile({"jpa", "postgresql"})
@Repository
interface JpaPatientRepository extends JpaRepository<Patient, Integer>, PatientRepository {

}
