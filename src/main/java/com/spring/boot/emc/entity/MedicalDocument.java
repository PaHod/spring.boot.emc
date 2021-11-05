package com.spring.boot.emc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


/**
 * Many-to-One bi-directional
 * <p>
 * many MedicalDocument knows the owner (doctor) and doctor knows them
 */
@Entity
@Table(name = "medical_documents")
public class MedicalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "secreet_level")
    private String secretLevel;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public MedicalDocument() {
    }

    public MedicalDocument(int id, String name, String secretLevel) {
        this.id = id;
        this.name = name;
        this.secretLevel = secretLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "MedicalDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secretLevel='" + secretLevel + '\'' +
                '}';
    }
}
