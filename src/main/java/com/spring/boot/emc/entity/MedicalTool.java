package com.spring.boot.emc.entity;

import javax.persistence.*;


/**
 * Many-to-One uni-directional
 * <p>
 * many MedicalTool don't care about the owner (doctor) but doctor knows them all
 */
@Entity
@Table(name = "medical_tools")
public class MedicalTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "seriar_number")
    private String serialNumber;

    public MedicalTool() {
    }

    public MedicalTool(int id, String name, String size, String serialNumber) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.serialNumber = serialNumber;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "MedicalTool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
