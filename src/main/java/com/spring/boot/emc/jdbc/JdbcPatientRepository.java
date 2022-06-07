package com.spring.boot.emc.jdbc;


import com.spring.boot.emc.model.Patient;
import com.spring.boot.emc.repository.PatientRepository;
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
public class JdbcPatientRepository implements PatientRepository {

    public static final String SQL_INSERT_PATIENT =
            " insert into patients( " +
            "  first_name, " +
            "  last_name, " +
            "  sex, " +
            "  address, " +
            "  phone_number " +
            " ) values (?,?,?,?,?) "
            ;

    private static final String SQL_SELECT_PATIENTS_JOIN_DOCTORS =
           " SELECT " +
           "  patients.id, patients.first_name, patients.last_name, " +
           "  patients.sex, patients.address, patients.phone_number, " +
           " doctors.id, doctors.first_name, doctors.last_name, doctors.speciality " +
           " FROM patients " +
           " LEFT OUTER JOIN patients_doctors " +
           " ON patients.id = patients_doctors.patient_id " +
           " LEFT OUTER JOIN doctors " +
           " ON doctors.id = patients_doctors.doctor_id "
           ;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Patient save(Patient patient) {
        return updatePatient(patient);
    }

//    @Override
//    public Patient update(Patient patient) {
//        return updatePatient(patient);
//    }

    private Patient updatePatient(Patient patient) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_INSERT_PATIENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getSex());
            ps.setString(4, patient.getAddress());
            ps.setString(5, patient.getPhoneNumber());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        patient.setId(key.intValue());
        return patient;
    }

    public Optional<Patient> findById(Integer id) {
        try {
            String getPatientSQL = SQL_SELECT_PATIENTS_JOIN_DOCTORS + " where patients.id = " + id;
            Patient patient = jdbcTemplate.queryForObject(getPatientSQL, JdbcResultSetMapper::mapPatient);
            return Optional.ofNullable(patient);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Patient> findAll() {
        return jdbcTemplate
                .queryForObject(SQL_SELECT_PATIENTS_JOIN_DOCTORS, JdbcResultSetMapper::mapPatientsList);
    }

    @Override
    public void deleteById(Integer id) {
        // TODO
    }
}
