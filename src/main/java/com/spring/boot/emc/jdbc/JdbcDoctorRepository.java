package com.spring.boot.emc.jdbc;


import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Profile("jdbc_template")
@Repository
public class JdbcDoctorRepository implements DoctorRepository {

    private static final String SQL_INSERT_DOCTOR = """
            insert into doctors(
             first_name,
             last_name,
             sex,
             address,
             phone_number, 
             speciality 
            ) values (?,?,?,?,?,?)
            """;
    private static final String SQL_SELECT_DOCTORS_JOIN_PATIENTS = """
            SELECT
             doctors.id, doctors.first_name, doctors.last_name, doctors.sex,
             doctors.address, doctors.phone_number, doctors.speciality,
             patients.id, patients.first_name, patients.last_name
            FROM doctors
            LEFT OUTER JOIN patients_doctors
             ON doctors.id = patients_doctors.doctor_id
            LEFT OUTER JOIN patients
             ON patients.id = patients_doctors.patient_id
            """;


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return updateDoctor(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return updateDoctor(doctor);
    }

    private Doctor updateDoctor(Doctor doctor) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_INSERT_DOCTOR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getSex());
            ps.setString(4, doctor.getAddress());
            ps.setString(5, doctor.getPhoneNumber());
            ps.setString(6, doctor.getSpeciality());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        doctor.setId(key.intValue());
        return doctor;
    }

    @Override
    public Optional<Doctor> findById(int id) {
        try {
            String getPatientSQL = SQL_SELECT_DOCTORS_JOIN_PATIENTS +
                    " where doctors.id = " + id;
            Doctor doctor = jdbcTemplate.queryForObject(getPatientSQL, JdbcResultSetMapper::mapDoctor);
            return Optional.ofNullable(doctor);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Doctor> findAll() {
        return jdbcTemplate.queryForObject(SQL_SELECT_DOCTORS_JOIN_PATIENTS,
                JdbcResultSetMapper::mapDoctorsList);
    }

    @Override
    public void deleteById(int id) {
        // TODO
    }
}
