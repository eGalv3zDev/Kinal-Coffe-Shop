package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.UsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModUsuarioConCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuarioConCreditoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface UsuarioConCreditoMapper {

    @Mapping(source = "idUsuarioCredito", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "apellido", target = "lastName")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "genero", target = "gender")
    @Mapping(source = "fechaNacimiento", target = "dateBirth")
    @Mapping(source = "contraseña", target = "password")
    UsuarioConCreditoDto toDto(UsuarioConCreditoEntity entity);
    List<UsuarioConCreditoDto> toDto(Iterable<UsuarioConCreditoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "gender", target = "genero", qualifiedByName = "generarGenero")
    UsuarioConCreditoEntity toEntity(UsuarioConCreditoDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "lastName", target = "apellido")
    @Mapping(source = "phone", target = "telefono")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "gender", target = "genero")
    @Mapping(source = "dateBirth", target = "fechaNacimiento")
    @Mapping(source = "password", target = "contraseña")
    void modificarEntityFromDto(ModUsuarioConCreditoDto mod, @MappingTarget UsuarioConCreditoEntity entity);
}