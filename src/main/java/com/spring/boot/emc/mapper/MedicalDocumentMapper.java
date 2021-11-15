package com.spring.boot.emc.mapper;

import com.spring.boot.emc.dto.MedicalDocumentDTO;
import com.spring.boot.emc.dto.MedicalDocumentSimpleDTO;
import com.spring.boot.emc.entity.MedicalDocument;
import org.springframework.stereotype.Component;


@Component
public class MedicalDocumentMapper {
    public MedicalDocument toEntity(MedicalDocumentDTO medicalDocumentDTO) {
        return new MedicalDocument();
    }


    public MedicalDocumentSimpleDTO toSimpleDTO(MedicalDocument medicalDocument) {
        return new MedicalDocumentSimpleDTO();
    }
}
