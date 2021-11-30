package com.spring.boot.emc.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @ManyToMany(mappedBy = "doctors", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<Patient> patients;

    public void addPatient(Patient newPatient) {
        if (patients == null) {
            patients = new ArrayList<>();
        }
        patients.add(newPatient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(sex, doctor.sex) && Objects.equals(address, doctor.address) && Objects.equals(phoneNumber, doctor.phoneNumber) && Objects.equals(speciality, doctor.speciality) && Objects.equals(patients, doctor.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sex, address, phoneNumber, speciality, patients);
    }
}
