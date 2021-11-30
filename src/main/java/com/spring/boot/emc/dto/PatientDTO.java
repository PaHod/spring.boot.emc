package com.spring.boot.emc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PatientDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private String address;
    private String phoneNumber;
    private List<DoctorDTO> doctors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientDTO)) return false;
        PatientDTO that = (PatientDTO) o;
        return id == that.id
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(sex, that.sex)
                && Objects.equals(address, that.address)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(doctors, that.doctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sex, address, phoneNumber, doctors);
    }
}
