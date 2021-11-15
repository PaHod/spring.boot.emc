package com.spring.boot.emc.mapper;

import com.spring.boot.emc.dto.MedicalToolDTO;
import com.spring.boot.emc.dto.MedicalToolSimpleDTO;
import com.spring.boot.emc.entity.MedicalTool;
import org.springframework.stereotype.Component;

@Component
public class MedicalToolMapper {
    public MedicalTool toEntity(MedicalToolDTO medicalToolDTO) {
        return new MedicalTool();
    }

    public MedicalToolSimpleDTO toSimpleDTO(MedicalTool medicalDocument) {
        return new MedicalToolSimpleDTO();
    }

}
