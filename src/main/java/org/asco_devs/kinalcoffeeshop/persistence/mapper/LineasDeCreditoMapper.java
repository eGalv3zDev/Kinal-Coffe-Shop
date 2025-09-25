package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.LineasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineasDeCredito;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineasDeCreditoMapper {
    LineasDeCreditoDto toDto(LineasDeCredito linea);

    List<LineasDeCreditoDto> toDto(List<LineasDeCredito> lineas);

    @InheritInverseConfiguration
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    LineasDeCredito toEntity(LineasDeCreditoDto dto);
}