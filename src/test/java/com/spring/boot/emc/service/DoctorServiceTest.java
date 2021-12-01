package com.spring.boot.emc.service;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.exception.DoctorNotFoundException;
import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.repository.DoctorRepository;
import com.spring.boot.emc.welcome.WelcomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    WelcomeService welcomeService;

    @InjectMocks
    DoctorService toTest;

    @BeforeEach
    void reset() {
        Mockito.reset(doctorRepository);
        Mockito.reset(welcomeService);
    }

    @Test
    void save_repositoryCall() {
        //given
        Doctor doctorInput = buildDoctor(1); // TODO: 30.11.2021 empty doctor

        //when
        toTest.save(doctorInput);

        //then
        verify(doctorRepository).save(doctorInput);
    }

    @Test
    void save_welcomeServiceCall() {
        //given
        Doctor doctorInput = buildDoctor(1);
        int generatedId = 55;

        when(doctorRepository.save(any())).thenReturn(doctorInput);

        //when
        toTest.save(doctorInput);

        //then
        verify(welcomeService).sendGreetingsToNewDoctor(doctorInput);
    }

    @Test
    void save_returnValue() {
        //given
        Doctor doctorInput = buildDoctor(1);
        int generatedId = 55;

        Doctor doctorExpected = buildDoctor(1);
        doctorExpected.setId(generatedId);

        when(doctorRepository.save(any())).thenAnswer(invocation -> {
            Doctor doc = invocation.getArgument(0);
            doc.setId(generatedId);
            return doc;
        });

        //when
        Doctor savedDoctor = toTest.save(doctorInput);

        //then
        assertThat(savedDoctor).isEqualTo(doctorExpected);
    }

    @Test
    void findByIdOrThrow_repositoryCall() {
        //given
        int id = 55;
        Doctor doctor = buildDoctorWithId(1);
        doctor.setId(id);

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        //when
        toTest.findByIdOrThrow(id);

        //then
        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    void findByIdOrThrow_successfulFound() {
        //given
        int id = 55;

        Doctor doctor = buildDoctorWithId(1);
        doctor.setId(id);

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        //when
        Doctor byIdOrThrow = toTest.findByIdOrThrow(id);


        //then
        verify(doctorRepository, times(1)).findById(id);
        assertThat(byIdOrThrow).isEqualTo(doctor);
    }

    @Test
    void findByIdOrThrow_notFound_throwDoctorNotFound() {
        int id = 55;

        Doctor doctor = buildDoctorWithId(1);
        doctor.setId(id);

        when(doctorRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Assertions.assertThrows(DoctorNotFoundException.class, () -> toTest.findByIdOrThrow(id));

        //then
        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    void findAll() {
        //given
        Iterable<Doctor> iterable = IntStream.range(0, 4)
                .mapToObj(this::buildDoctor)
                .collect(toList());

        when(doctorRepository.findAll()).thenReturn(iterable);

        //when
        List<Doctor> doctorsList = toTest.findAll();


        //then
        verify(doctorRepository, times(1)).findAll();
        iterable.forEach(doctor ->
                assertThat(doctorsList.contains(doctor)).isTrue()
        );

    }

    @Test
    void update() {
        //given
        Doctor doctor = buildDoctorWithId(43);

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        //when
        Doctor updatedDoctor = toTest.update(doctor);

        //then
        verify(doctorRepository, times(1)).save(doctor);
        assertThat(updatedDoctor).isEqualTo(doctor);

    }

    @Test
    void removeById() {
        //given
        int idToRemove = 45;

        //when
        toTest.removeById(idToRemove);

        //then
        verify(doctorRepository, times(1)).deleteById(idToRemove);
    }

    private DoctorDTO buildDoctorDTO(final int suffix) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFirstName("Name_" + suffix);
        doctorDTO.setLastName("LastName_" + suffix);
        doctorDTO.setSex("Sex_" + suffix);
        doctorDTO.setAddress("Address_" + suffix);
        doctorDTO.setPhoneNumber("Phone_" + suffix);
        doctorDTO.setSpeciality("Speciality_" + suffix);
        return doctorDTO;
    }

    private Doctor buildDoctorWithId(final int suffix) {
        Doctor doctor = buildDoctor(suffix);
        doctor.setId(suffix);
        return doctor;
    }

    private Doctor buildDoctor(final int suffix) {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Name_" + suffix);
        doctor.setLastName("LastName_" + suffix);
        doctor.setSex("Sex_" + suffix);
        doctor.setAddress("Address_" + suffix);
        doctor.setPhoneNumber("Phone_" + suffix);
        doctor.setSpeciality("Speciality_" + suffix);
        return doctor;
    }
}