package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorDTO;
import com.spring.boot.emc.dto.mapper.DoctorMapper;
import com.spring.boot.emc.dto.mapper.PatientMapper;
import com.spring.boot.emc.model.Doctor;
import com.spring.boot.emc.service.DoctorService;
import com.spring.boot.emc.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @Mock
    PatientService patientService;

    @Mock
    DoctorService doctorService;

    @Spy
    DoctorMapper doctorMapper;

    @Spy
    PatientMapper patientMapper;

    @InjectMocks
    DoctorController doctorController;

    @BeforeEach
    void reset() {
        patientMapper.setDoctorMapper(doctorMapper);
        doctorMapper.setPatientMapper(patientMapper);
        Mockito.reset(patientService);
        Mockito.reset(doctorService);
        Mockito.reset(doctorMapper);
        Mockito.reset(patientMapper);
    }

    @Test
    void addDoctor_callMapper() {
        //given
        DoctorDTO doctorDTO1 = buildDoctorDTO(1);
        Doctor doctor1 = buildDoctor(1);

        // TODO: 30.11.2021 ArgumentCaptor better than thenAnswer
        when(doctorService.save(any())).thenAnswer((invocation) -> {
            Doctor d = invocation.getArgument(0);
            assertThat(d.getFirstName()).isEqualTo(doctor1.getFirstName());
            assertThat(d.getLastName()).isEqualTo(doctor1.getLastName());
            assertThat(d.getSex()).isEqualTo(doctor1.getSex());
            assertThat(d.getAddress()).isEqualTo(doctor1.getAddress());
            assertThat(d.getPhoneNumber()).isEqualTo(doctor1.getPhoneNumber());
            assertThat(d.getSpeciality()).isEqualTo(doctor1.getSpeciality());
            return d;
        });

        //when
        doctorController.addDoctor(doctorDTO1);

        //then
        verify(doctorMapper, times(1)).toNewEntity(doctorDTO1);
    }

    @Test
    void addDoctor_patientServiceSave_callOnce() {
        //given
        DoctorDTO doctorDTO1 = buildDoctorDTO(1);
        Doctor doctor1 = doctorMapper.toNewEntity(doctorDTO1);
        when(doctorService.save(any())).thenReturn(doctor1);

        //when
        doctorController.addDoctor(doctorDTO1);

        //then
        verify(doctorService, times(1)).save(doctor1);
    }

    @Test
    void addDoctor_correctMapToDTO() {
        //given
        DoctorDTO doctorDTO1 = buildDoctorDTO(1);
        Doctor doctor1 = buildDoctor(1);
        int generatedId = 25;

        when(doctorService.save(doctor1)).thenAnswer(invocation -> {
            Doctor doctor = buildDoctor(1);
            doctor.setId(generatedId);
            return doctor;
        });

        //when
        DoctorDTO doctorDTO = doctorController.addDoctor(doctorDTO1);

        //then
        verify(doctorService, times(1)).save(doctor1);
        doctor1.setId(generatedId);
        assertEntityEqualsToDTO(doctor1, doctorDTO);
    }

    @Test
    void allDoctors_findAllCall() {
        //given
        List<Doctor> doctors = IntStream.range(0, 3)
                .mapToObj(this::buildDoctor).collect(toList());
        when(doctorService.findAll()).thenReturn(doctors);

        //when
        doctorController.allDoctors();

        //then
        verify(doctorService, only()).findAll();
        verify(doctorMapper, times(doctors.size())).toDtoMainFields(any());

    }

    @Test
    void allDoctors_getExpectedData() {
        //given
        List<Doctor> doctors = IntStream.range(0, 3)
                .mapToObj(this::buildDoctorWithId).collect(toList());

        when(doctorService.findAll()).thenReturn(doctors);

        //when
        List<DoctorDTO> doctorDTOS = doctorController.allDoctors();

        //then
        for (int i = 0; i < doctorDTOS.size(); i++) {
            DoctorDTO doctorDTO = doctorDTOS.get(i);
            Doctor doctor = doctors.get(i);
            assertThat(doctorDTO.getId()).isEqualTo(doctor.getId());
            assertThat(doctorDTO.getFirstName()).isEqualTo(doctor.getFirstName());
            assertThat(doctorDTO.getLastName()).isEqualTo(doctor.getLastName());
            assertThat(doctorDTO.getSpeciality()).isEqualTo((doctor.getSpeciality()));
            assertThat(doctorDTO.getSex()).isNull();
            assertThat(doctorDTO.getAddress()).isNull();
            assertThat(doctorDTO.getPhoneNumber()).isNull();
        }
    }

    @Test
    void getDoctor_correctServiceCall() {
        //given
        int id = 5;
        Doctor doctor = buildDoctorWithId(id);

        when(doctorService.findByIdOrThrow(id))
                .thenReturn(doctor);

        //when
        doctorController.getDoctor(id);

        //then
        verify(doctorService, only()).findByIdOrThrow(id);
        verify(doctorMapper, only()).toDTO(doctor);
    }

    @Test
    void getDoctor_correctMappingToDto() {
        //given
        int id = 5;
        Doctor doctor = buildDoctorWithId(id);

        when(doctorService.findByIdOrThrow(id)).thenReturn(doctor);

        //when
        DoctorDTO doctorDTO = doctorController.getDoctor(id);

        //then
        verify(doctorMapper, only()).toDTO(doctor);
        assertEntityEqualsToDTO(doctor, doctorDTO);
    }

    @Test
    void updateDoctor_serviceFindByIdCall() {
        //given
        int id = 5;
        String newName = "NEW Name";
        Doctor doctorOld = buildDoctorWithId(id);

        Doctor doctorExpected = buildDoctorWithId(id);
        doctorExpected.setFirstName(newName);

        DoctorDTO doctorDTO = buildDoctorDTOWithId(id);
        doctorDTO.setFirstName(newName);

        when(doctorService.findByIdOrThrow(id))
                .thenReturn(doctorOld);

        //when
        doctorController.updateDoctor(id, doctorDTO);

        //then
        verify(doctorService, times(1)).findByIdOrThrow(id);
        verify(doctorMapper, times(1))
                .updateEntityFromDTO(doctorOld, doctorDTO);
    }

    @Test
    void updateDoctor_mapperUpdateFromEntityCall() {
        //given
        int id = 5;
        String newName = "NEW Name";
        Doctor doctorOld = buildDoctorWithId(id);

        Doctor doctorExpected = buildDoctorWithId(id);
        doctorExpected.setFirstName(newName);

        DoctorDTO doctorDTO = buildDoctorDTOWithId(id);
        doctorDTO.setFirstName(newName);

        when(doctorService.findByIdOrThrow(id))
                .thenReturn(doctorOld);

        //when
        doctorController.updateDoctor(id, doctorDTO);

        //then
        verify(doctorService, times(1)).findByIdOrThrow(id);
        verify(doctorMapper, times(1))
                .updateEntityFromDTO(doctorOld, doctorDTO);
    }


    private void assertEntityEqualsToDTO(Doctor doctor1, DoctorDTO doctorDTO) {
        assertThat(doctorDTO.getId()).isEqualTo(doctor1.getId());
        assertThat(doctorDTO.getFirstName()).isEqualTo(doctor1.getFirstName());
        assertThat(doctorDTO.getLastName()).isEqualTo(doctor1.getLastName());
        assertThat(doctorDTO.getSex()).isEqualTo(doctor1.getSex());
        assertThat(doctorDTO.getAddress()).isEqualTo(doctor1.getAddress());
        assertThat(doctorDTO.getPhoneNumber()).isEqualTo(doctor1.getPhoneNumber());
        assertThat(doctorDTO.getSpeciality()).isEqualTo(doctor1.getSpeciality());
    }

    private DoctorDTO buildDoctorDTOWithId(final int suffix) {
        DoctorDTO doctorDTO = buildDoctorDTO(suffix);
        doctorDTO.setId(suffix);
        return doctorDTO;
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