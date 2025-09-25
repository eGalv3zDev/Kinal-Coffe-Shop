package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.CategoriaProductosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaProductos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaProductosMapper {

    CategoriaProductosDto toDto(CategoriaProductos categoria);
    List<CategoriaProductosDto> toDto(List<CategoriaProductos> categorias);

    @InheritInverseConfiguration
    CategoriaProductos toEntity(CategoriaProductosDto dto);
}