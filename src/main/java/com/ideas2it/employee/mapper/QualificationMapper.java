package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.QualificationDto;
import com.ideas2it.employee.models.Qualification;

public class QualificationMapper {
    public static Qualification convertQualificationDtoToQualification(QualificationDto qualificationDto) {
        Qualification qualification   = new Qualification();
        qualification.setDescription(qualificationDto.getDescription());
        return qualification;
    }

    public static QualificationDto convertQualificationToQualificationDto(Qualification qualification) {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setDescription(qualification.getDescription());
        return qualificationDto;
    }
}
