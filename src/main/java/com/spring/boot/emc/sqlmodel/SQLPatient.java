package com.spring.boot.emc.sqlmodel;

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
@Table(name = "patients")
public class SQLPatient {

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "patients_doctors",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    @ToString.Exclude
    private List<SQLDoctor> doctors;

    public void addDoctor(SQLDoctor doctor) {
        if (doctors == null) {
            doctors = new ArrayList<>();
        }
        doctors.add(doctor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SQLPatient)) return false;
        SQLPatient patient = (SQLPatient) o;
        return id == patient.id
                && Objects.equals(firstName, patient.firstName)
                && Objects.equals(lastName, patient.lastName)
                && Objects.equals(sex, patient.sex)
                && Objects.equals(address, patient.address)
                && Objects.equals(phoneNumber, patient.phoneNumber)
                && Objects.equals(doctors, patient.doctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sex, address, phoneNumber, doctors);
    }
}


