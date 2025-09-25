package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.AlumnosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Alumnos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnosMapper {

    AlumnosDto toDto(Alumnos alumno);
    List<AlumnosDto> toDto(List<Alumnos> alumnos);

    @InheritInverseConfiguration
    Alumnos toEntity(AlumnosDto alumnosDto);
}