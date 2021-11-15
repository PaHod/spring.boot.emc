package com.spring.boot.emc.mapper;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.dto.DoctorSimpleDTO;
import com.spring.boot.emc.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * can be simplified by using mapper framework such as:
 * mapstruct,
 * orika-mapper
 */

@Component
public class DoctorMapper {

    private PatientMapper patientMapper;
    private MedicalDocumentMapper documentMapper;
    private MedicalToolMapper toolMapper;

    @Autowired
    public void setPatientMapper(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @Autowired
    public void setDocumentMapper(MedicalDocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @Autowired
    public void setToolMapper(MedicalToolMapper toolMapper) {
        this.toolMapper = toolMapper;
    }

    /**
     * skip id and complex field like: patients, documents etc.
     *
     * @return new Doctor Entity
     */
    public Doctor doctorToNewEntity(DoctorDTO doctorDTO) {
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

        doctorDTO.setMedicalDocumentsDTOS(streamOfNullable(doctor.getMedicalDocuments())
                .map(documentMapper::toSimpleDTO)
                .collect(toList()));

        doctorDTO.setMedicalToolsDTOS(streamOfNullable(doctor.getMedicalTools())
                .map(toolMapper::toSimpleDTO)
                .collect(toList()));

        doctorDTO.setPatientsDTOS(streamOfNullable(doctor.getPatients())
                .map(patientMapper::toSimpleDTO)
                .collect(toList()));

        return doctorDTO;
    }


    private <P> Stream<P> streamOfNullable(List<P> list) {
        return ofNullable(list)
                .orElse(Collections.emptyList())
                .stream();
    }

    public DoctorSimpleDTO toSimpleDTO(Doctor doctor) {
        DoctorSimpleDTO doctorSimpleDTO = new DoctorSimpleDTO();
        doctorSimpleDTO.setId(doctor.getId());
        doctorSimpleDTO.setFirstName(doctor.getFirstName());
        doctorSimpleDTO.setLastName(doctor.getLastName());
        doctorSimpleDTO.setSpeciality(doctor.getSpeciality());
        return doctorSimpleDTO;
    }

}
