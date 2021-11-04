package com.spring.boot.emc.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "speciality")
    private String speciality;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "patients_doctors",
//            joinColumns = @JoinColumn(name = "doctor_id"),
//            inverseJoinColumns = @JoinColumn(name = "patient_id")
//    )
//    private List<Patient> patients;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String sex, String address, String phoneNumber, String speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.speciality = speciality;
    }

//    public void addPatientToDoctor(Patient newPatient) {
//        if (patients == null) {
//            patients = new ArrayList<>();
//        }
//        patients.add(newPatient);
//    }

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

//    public List<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(List<Patient> patients) {
//        this.patients = patients;
//    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
