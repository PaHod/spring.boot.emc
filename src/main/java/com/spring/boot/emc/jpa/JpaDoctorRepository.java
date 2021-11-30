package com.spring.boot.emc.jpa;

import com.spring.boot.emc.repository.DoctorRepository;
import com.spring.boot.emc.model.Doctor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository
interface JpaDoctorRepository extends JpaRepository<Doctor, Integer>, DoctorRepository {

}
