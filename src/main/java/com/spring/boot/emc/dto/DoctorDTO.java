package com.spring.boot.emc.dto;

import java.util.List;

public class DoctorDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private String address;
    private String phoneNumber;
    private String speciality;

    private List<MedicalDocumentSimpleDTO> medicalDocumentsDTOS;
    private List<MedicalToolSimpleDTO> medicalToolsDTOS;
    private List<PatientSimpleDTO> patientsDTOS;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<MedicalDocumentSimpleDTO> getMedicalDocumentsDTOS() {
        return medicalDocumentsDTOS;
    }

    public void setMedicalDocumentsDTOS(List<MedicalDocumentSimpleDTO> medicalDocumentsDTOS) {
        this.medicalDocumentsDTOS = medicalDocumentsDTOS;
    }

    public List<MedicalToolSimpleDTO> getMedicalToolsDTOS() {
        return medicalToolsDTOS;
    }

    public void setMedicalToolsDTOS(List<MedicalToolSimpleDTO> medicalToolsDTOS) {
        this.medicalToolsDTOS = medicalToolsDTOS;
    }

    public List<PatientSimpleDTO> getPatientsDTOS() {
        return patientsDTOS;
    }

    public void setPatientsDTOS(List<PatientSimpleDTO> patientsDTOS) {
        this.patientsDTOS = patientsDTOS;
    }
}
