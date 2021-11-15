package com.spring.boot.emc.controller;

import com.spring.boot.emc.dto.DoctorSimpleDTO;
import com.spring.boot.emc.dto.PatientDTO;
import com.spring.boot.emc.dto.PatientSimpleDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ModelHelper {

    public <R extends DoctorSimpleDTO> EntityModel<R> toModelWithLinksDoctor(R dto) {
        return EntityModel.of(dto)
                .add(linkTo(methodOn(EmcDoctorRestController.class).getDoctor(dto.getId())).withSelfRel());
    }

    public <R extends PatientDTO> EntityModel<R> toModelWithLinksPatient(R dto) {
        return EntityModel.of(dto)
                .add(getPatientSelfLink(dto.getId()))
                .add(getPatientDoctorsLink(dto.getId()).withRel("doctors"))
                .add(getPatientDeleteLink(dto.getId()));
    }

    private <R extends PatientDTO> WebMvcLinkBuilder getPatientDoctorsLink(int id) {
        return linkTo(methodOn(EmcPatientsHateoasController.class).getPatientsDoctors(id));
    }

    public <R extends PatientSimpleDTO> EntityModel<R> toModelWithLinksPatientSimple(R dto) {
        return EntityModel.of(dto)
                .add(getPatientSelfLink(dto.getId()))
                .add(getPatientDeleteLink(dto.getId()));
    }


    private Link getPatientDeleteLink(int id) {
        return linkTo(methodOn(EmcPatientsHateoasController.class).removePatient(id)).withRel("delete");
    }

    private Link getPatientSelfLink(int id) {
        return linkTo(methodOn(EmcPatientsHateoasController.class).getPatient(id)).withSelfRel();
    }

    // TODO: check Affordances feature
    private Link getPatientSelfLinkWithAffordance(int id) {
        Link link = linkTo(methodOn(EmcPatientsHateoasController.class).getPatient(id)).withSelfRel();

        return Affordances.of(link)
                .afford(HttpMethod.GET)
                .withInput(Integer.class)
                .withOutput(ResponseEntity.class)
                .withName("get")

                .andAfford(HttpMethod.PUT)
                .withInput(Integer.class)
                .withOutput(ResponseEntity.class)
                .withName("update")

                .andAfford(HttpMethod.DELETE)
                .withInput(Integer.class)
                .withName("delete")
                .toLink();
    }

    public <R> CollectionModel<R> toCollectionModelWithDoctorsLink(List<R> modelList, int patientId) {
        return CollectionModel.of(modelList)
                .add(getPatientDoctorsLink(patientId).withSelfRel());
    }
}
