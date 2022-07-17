package com.spring.boot.emc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private String address;
    private String phoneNumber;
    private String speciality;

    private List<PatientDTO> patientsDTOS;

//    public static Doctor updateEntityFromDTO(Doctor doctor, DoctorDTO doctorDTO) {
//        doctor.setFirstName(ofNullable(doctorDTO.getFirstName()).orElse(doctor.getFirstName()));
//        doctor.setLastName(ofNullable(doctorDTO.getLastName()).orElse(doctor.getLastName()));
//        doctor.setSex(ofNullable(doctorDTO.getSex()).orElse(doctor.getSex()));
//        doctor.setAddress(ofNullable(doctorDTO.getAddress()).orElse(doctor.getAddress()));
//        doctor.setPhoneNumber(ofNullable(doctorDTO.getPhoneNumber()).orElse(doctor.getPhoneNumber()));
//        doctor.setSpeciality(ofNullable(doctorDTO.getSpeciality()).orElse(doctor.getSpeciality()));
//        return doctor;
//    }
//
//    public static DoctorDTO toDTO(Doctor doctor) {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setId(doctor.getId());
//        doctorDTO.setFirstName(doctor.getFirstName());
//        doctorDTO.setLastName(doctor.getLastName());
//        doctorDTO.setSex(doctor.getSex());
//        doctorDTO.setAddress(doctor.getAddress());
//        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
//        doctorDTO.setSpeciality(doctor.getSpeciality());

//        doctorDTO.setPatientsDTOS(streamOfNullable(doctor.getPatients())
//                .map(patientMapper::toDtoMainFields)
//                .collect(toList()));

//        return doctorDTO;
//    }
}
