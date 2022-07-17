package com.spring.boot.emc.dynamobd;

import com.spring.boot.emc.dynamobd.model.DynamoDBDoctor;
import com.spring.boot.emc.model.Doctor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dynamodb"})
@Service
public class DynamoDBDoctorEntityConverter {


    public DynamoDBDoctor toDynamo(Doctor doctor) {

        DynamoDBDoctor dynamoDoctor = new DynamoDBDoctor();

        dynamoDoctor.setDoctorId(doctor.getId());
        dynamoDoctor.setFirstName(doctor.getFirstName());
        dynamoDoctor.setLastName(doctor.getLastName());
        dynamoDoctor.setSex(doctor.getSex());
        dynamoDoctor.setAddress(doctor.getAddress());
        dynamoDoctor.setPhoneNumber(doctor.getPhoneNumber());
        dynamoDoctor.setSpeciality(doctor.getSpeciality());

        return dynamoDoctor;

    }

    public Doctor toModel(DynamoDBDoctor dynamoDoctor) {

        Doctor doctor = new Doctor();

        doctor.setId(dynamoDoctor.getDoctorId());
        doctor.setFirstName(dynamoDoctor.getFirstName());
        doctor.setLastName(dynamoDoctor.getLastName());
        doctor.setSex(dynamoDoctor.getSex());
        doctor.setAddress(dynamoDoctor.getAddress());
        doctor.setPhoneNumber(dynamoDoctor.getPhoneNumber());
        doctor.setSpeciality(dynamoDoctor.getSpeciality());

        return doctor;


    }
}
