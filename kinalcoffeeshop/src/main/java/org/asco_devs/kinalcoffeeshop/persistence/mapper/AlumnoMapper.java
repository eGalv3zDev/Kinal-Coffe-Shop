package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.AlumnoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModAlumnoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.AlumnoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface AlumnoMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastName")
    @Mapping(source = "carnet", target = "carnet")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "genero", target = "genre")
    @Mapping(source = "fechaNacimiento", target = "dateBirth")
    @Mapping(source = "contraseña", target = "password")
    AlumnoDto toDto(AlumnoEntity entity);
    List<AlumnoDto> toDto(Iterable<AlumnoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source="genre", target="genero", qualifiedByName = "generarGenero")
    AlumnoEntity toEntity(AlumnoDto dto);


    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastName", target = "apellido")
    @Mapping(source = "carnet", target = "carnet")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "genre", target = "genero")
    @Mapping(source = "dateBirth", target = "fechaNacimiento")
    @Mapping(source = "password", target = "contraseña")
    void modificarEntityFromDto(ModAlumnoDto mod, @MappingTarget AlumnoEntity entity);
}
