package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaProductosMapper {

    @Mapping(source = "idCategoria", target = "idCategoria")
    @Mapping(source = "nombre", target = "nombre")
    CategoriaProductosDto toDto(CategoriaProductos categoria);

    List<CategoriaProductosDto> toDto(List<CategoriaProductos> categorias);

    @Mapping(source = "idCategoria", target = "idCategoria")
    @Mapping(source = "nombre", target = "nombre")
    CategoriaProductos toEntity(CategoriaProductosDto dto);
}