package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(source = "idCategoria", target = "id")
    @Mapping(source = "nombre", target = "name")
    CategoriaDto toDto(CategoriaEntity entity);
    List<CategoriaDto> toDto(Iterable<CategoriaEntity> entities);

    @Mapping(source = "name", target = "nombre")
    CategoriaEntity toEntity(CategoriaDto dto);

    @Mapping(source = "name", target = "nombre")
    void modificarEntityFromDto(ModCategoriaDto mod, @MappingTarget CategoriaEntity entity);
}