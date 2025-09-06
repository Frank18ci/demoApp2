package org.carpio.com.demoapp4.util;

import org.carpio.com.demoapp4.dto.PatientDto;
import org.carpio.com.demoapp4.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCIA = Mappers.getMapper(PatientMapper.class);
    @Mapping(source = "fullName", target = "nombres")
    @Mapping(source = "document", target = "documento")
    PatientDto patientToPatientDto(Patient patient);

    @Mapping(source = "nombres", target = "fullName")
    @Mapping(source = "documento", target = "document")
    Patient patientDtoToPatient(PatientDto patientDto);
}
