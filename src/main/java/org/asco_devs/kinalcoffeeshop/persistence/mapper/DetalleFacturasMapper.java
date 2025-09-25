package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetalleFacturasDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleFacturasMapper {

    DetalleFacturasDto toDto(DetalleFacturas detalle);
    List<DetalleFacturasDto> toDto(List<DetalleFacturas> detalles);

    @InheritInverseConfiguration
    @Mapping(target = "factura", ignore = true)
    @Mapping(target = "producto", ignore = true)
    DetalleFacturas toEntity(DetalleFacturasDto dto);
}