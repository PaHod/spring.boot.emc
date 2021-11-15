package com.spring.boot.emc.dto;

public class PatientSimpleDTO {

    private int id;
    private String firstName;
    private String lastName;


    public PatientSimpleDTO() {

//        add()
    }

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
}
