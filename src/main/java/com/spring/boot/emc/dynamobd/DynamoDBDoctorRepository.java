package com.spring.boot.emc.dynamobd;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.spring.boot.emc.dynamobd.model.DynamoDBDoctor;
import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Profile({"dynamodb"})
@Repository
public class DynamoDBDoctorRepository implements DoctorRepository {

    @Autowired
    DynamoDBMapper dynamoDBMapper;

    @Autowired
    DynamoDBDoctorEntityConverter entityConverter;

    @Override
    public Doctor save(Doctor doctor) {
        DynamoDBDoctor dynamoDoctor = entityConverter.toDynamo(doctor);
        dynamoDBMapper.save(dynamoDoctor);
        return entityConverter.toModel(dynamoDoctor);
    }

    @Override
    public Optional<Doctor> findById(int id) {
        DynamoDBDoctor dynamoDoctor = dynamoDBMapper.load(DynamoDBDoctor.class, id);
        entityConverter.toModel(dynamoDoctor);
        return Optional.ofNullable(entityConverter.toModel(dynamoDoctor));
    }

    @Override
    public Iterable<Doctor> findAll() {
        PaginatedScanList<DynamoDBDoctor> dynamoDoctorsList =
                dynamoDBMapper.scan(DynamoDBDoctor.class, new DynamoDBScanExpression());

        return dynamoDoctorsList.stream()
                .map(dynamoDoctor -> entityConverter.toModel(dynamoDoctor))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        DynamoDBDoctor dbDoctor = dynamoDBMapper.load(DynamoDBDoctor.class, id);
        dynamoDBMapper.delete(dbDoctor);
    }
}
