package com.spring.boot.emc.jdbc;

import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.model.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcResultSetMapper {

    public static Patient mapPatient(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = null;
        do {
            if (patient == null) {
                patient = buildFullPatientFromResultSet(rs);
            }

            Doctor doctor = buildShortDoctorFromResultSet(rs);
            patient.addDoctor(doctor);
        } while (rs.next());

        return patient;
    }

    public static Iterable<Patient> mapPatientsList(ResultSet rs, int rowNum) throws SQLException {
        Map<Integer, Patient> patients = new LinkedHashMap<>();
        do {
            int patientId = rs.getInt("patients.id");
            Patient patient = patients.get(patientId);
            if (patient == null) {
                patient = buildFullPatientFromResultSet(rs);
                patients.put(patientId, patient);
            }

            Doctor doctor = buildShortDoctorFromResultSet(rs);
            patient.addDoctor(doctor);
        } while (rs.next());

        return patients.values();
    }

    public static Doctor mapDoctor(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = null;
        do {
            if (doctor == null) {
                doctor = buildFullDoctorFromResultSet(rs);
            }

            Patient p = buildShortPatientFromResultSet(rs);
            doctor.addPatient(p);
        } while (rs.next());

        return doctor;
    }

    public static Iterable<Doctor> mapDoctorsList(ResultSet rs, int rowNum) throws SQLException {
        Map<Integer, Doctor> doctors = new LinkedHashMap<>();
        do {
            int doctorId = rs.getInt("doctors.id");
            Doctor doctor = doctors.get(doctorId);
            if (doctor == null) {
                doctor = buildFullDoctorFromResultSet(rs);
                doctors.put(doctorId, doctor);
            }

            Patient patient = buildShortPatientFromResultSet(rs);
            doctor.addPatient(patient);
        } while (rs.next());

        return doctors.values();
    }

    private static Doctor buildShortDoctorFromResultSet(ResultSet rs) throws SQLException {
        return buildDoctorFromResultSet(rs).build();
    }

    private static Doctor buildFullDoctorFromResultSet(ResultSet rs) throws SQLException {
        return buildDoctorFromResultSet(rs)
                .sex(rs.getString("doctors.sex"))
                .address(rs.getString("doctors.address"))
                .phoneNumber(rs.getString("doctors.phone_number"))
                .build();
    }

    private static Doctor.DoctorBuilder buildDoctorFromResultSet(ResultSet rs) throws SQLException {
        return Doctor.builder()
                .id(rs.getInt("doctors.id"))
                .firstName(rs.getString("doctors.first_name"))
                .lastName(rs.getString("doctors.last_name"))
                .speciality(rs.getString("doctors.speciality"));
    }

    private static Patient buildShortPatientFromResultSet(ResultSet rs) throws SQLException {
        return buildPatientFromResultSet(rs).build();
    }

    private static Patient buildFullPatientFromResultSet(ResultSet rs) throws SQLException {
        return buildPatientFromResultSet(rs)
                .sex(rs.getString("patients.sex"))
                .address(rs.getString("patients.address"))
                .phoneNumber(rs.getString("patients.phone_number"))
                .build();
    }

    private static Patient.PatientBuilder buildPatientFromResultSet(ResultSet rs) throws SQLException {
        return Patient.builder()
                .id(rs.getInt("patients.id"))
                .firstName(rs.getString("patients.first_name"))
                .lastName(rs.getString("patients.last_name"));
    }
}
