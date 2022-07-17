package com.spring.boot.emc.dynamobd;

import com.spring.boot.emc.model.Patient;
import com.spring.boot.emc.repository.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Profile({"dynamodb"})
@Repository
public class DynamoDBPatientRepository implements PatientRepository {

    @Override
    public Patient save(Patient patient) {
        return null;
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Patient> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
