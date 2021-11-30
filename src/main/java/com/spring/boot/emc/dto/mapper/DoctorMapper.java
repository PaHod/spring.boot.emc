package com.spring.boot.emc.dto.mapper;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * can be simplified by using mapper framework such as:
 * modelmapper,
 * mapstruct,
 * orika-mapper
 */

@Component
public class DoctorMapper {

    private PatientMapper patientMapper;

    @Autowired
    public void setPatientMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    public Doctor toNewEntity(DoctorDTO doctorDTO) {
        return updateEntityFromDTO(new Doctor(), doctorDTO);
    }

    public Doctor updateEntityFromDTO(Doctor doctor, DoctorDTO doctorDTO) {
        doctor.setFirstName(ofNullable(doctorDTO.getFirstName()).orElse(doctor.getFirstName()));
        doctor.setLastName(ofNullable(doctorDTO.getLastName()).orElse(doctor.getLastName()));
        doctor.setSex(ofNullable(doctorDTO.getSex()).orElse(doctor.getSex()));
        doctor.setAddress(ofNullable(doctorDTO.getAddress()).orElse(doctor.getAddress()));
        doctor.setPhoneNumber(ofNullable(doctorDTO.getPhoneNumber()).orElse(doctor.getPhoneNumber()));
        doctor.setSpeciality(ofNullable(doctorDTO.getSpeciality()).orElse(doctor.getSpeciality()));
        return doctor;
    }

    public DoctorDTO toDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setSex(doctor.getSex());
        doctorDTO.setAddress(doctor.getAddress());
        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
        doctorDTO.setSpeciality(doctor.getSpeciality());

        doctorDTO.setPatientsDTOS(streamOfNullable(doctor.getPatients())
                .map(patientMapper::toDtoMainFields)
                .collect(toList()));

        return doctorDTO;
    }


    public DoctorDTO toDtoMainFields(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setSpeciality(doctor.getSpeciality());
        return doctorDTO;
    }

    private <P> Stream<P> streamOfNullable(List<P> list) {
        return ofNullable(list)
                .orElse(Collections.emptyList())
                .stream();
    }

}
