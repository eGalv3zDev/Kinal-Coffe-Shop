package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.FacturasDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Facturas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturasMapper {

    FacturasDto toDto(Facturas factura);
    List<FacturasDto> toDto(List<Facturas> facturas);

    @InheritInverseConfiguration
    @Mapping(target = "pedido", ignore = true)
    Facturas toEntity(FacturasDto dto);
}