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
}
