package com.spring.boot.emc.dto;

import java.util.List;

public class PatientDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private String address;
    private String phoneNumber;
    private List<DoctorSimpleDTO> doctors;


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

    public List<DoctorSimpleDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorSimpleDTO> doctors) {
        this.doctors = doctors;
    }
}
