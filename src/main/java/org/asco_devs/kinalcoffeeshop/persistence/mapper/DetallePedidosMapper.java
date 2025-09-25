package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidosMapper {

    DetallePedidosDto toDto(DetallePedidos detalle);
    List<DetallePedidosDto> toDto(List<DetallePedidos> detalles);

    @InheritInverseConfiguration
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "producto", ignore = true)
    DetallePedidos toEntity(DetallePedidosDto dto);
}