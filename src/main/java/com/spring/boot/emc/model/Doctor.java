package com.spring.boot.emc.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
