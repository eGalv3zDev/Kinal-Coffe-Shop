package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.PedidosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pedidos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidosMapper {

    PedidosDto toDto(Pedidos pedido);
    List<PedidosDto> toDto(List<Pedidos> pedidos);

    @InheritInverseConfiguration
    @Mapping(target = "alumno", ignore = true)
    @Mapping(target = "usuarioConCredito", ignore = true)
    Pedidos toEntity(PedidosDto dto);
}