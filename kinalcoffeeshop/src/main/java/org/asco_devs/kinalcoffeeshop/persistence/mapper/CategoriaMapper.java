package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target="id"),
            @Mapping(source = "nombre", target="name")
    })
    CategoriaDto toDto(CategoriaEntity entity);

    List<CategoriaDto> toDto(Iterable<CategoriaEntity> entities);

    @Mappings({
            @Mapping(source = "id", target="idCategoria"),
            @Mapping(source = "name", target="nombre")
    })
    CategoriaEntity toEntity(CategoriaDto dto);

    @Mappings({
            @Mapping(target = "idCategoria", ignore = true),
            @Mapping(source = "name", target="nombre")
    })
    void modificarEntityFromDto(ModCategoriaDto mod, @MappingTarget CategoriaEntity entity);
}