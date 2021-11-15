package com.spring.boot.emc.repository;

import com.spring.boot.emc.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {

}
