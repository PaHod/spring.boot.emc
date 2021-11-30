package com.spring.boot.emc.service;

import com.spring.boot.emc.repository.PatientRepository;
import com.spring.boot.emc.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Autowired
    private IPatientService patientService;

    private PatientRepository patientRepository;

    @BeforeEach
    void setUpBeforeEach() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(patientRepository);

    }

    @Test
    void saveIsOk() {
        //given
        Patient patient = new Patient();
        patient.setFirstName("Name_1");
        patient.setLastName("LastName_1");
        patient.setSex("Sex_1");
        patient.setAddress("Address_1");
        patient.setPhoneNumber("Phone_1");

        int suggestId = new Random().nextInt();

        when(patientRepository.save(any())).thenAnswer(invocation -> {
            invocation.<Patient>getArgument(0).setId(suggestId);
            return null;
        });

        //when
        patientService.save(patient);

        //then
        assertThat(patient.getId()).isEqualTo(suggestId);
    }

    @Test
    void saveThrowNullPointerException() {
        //given
        Patient patient = null;

        //when
        assertThrows(NullPointerException.class, () -> patientService.save(patient));

        //then
        verify(patientRepository, never()).save(any());
    }
}