package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.asco_devs.kinalcoffeeshop.dominio.dto.DetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetallePedidosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidosEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidosMapper {

    @Mapping(source = "pedido.idPedido", target = "idPedido")
    @Mapping(source = "producto.idProducto", target = "idProducto")
    DetallePedidosDto toDto(DetallePedidosEntity entity);

    List<DetallePedidosDto> toDto(Iterable<DetallePedidosEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "idPedido", target = "pedido.idPedido")
    @Mapping(source = "idProducto", target = "producto.idProducto")
    DetallePedidosEntity toEntity(DetallePedidosDto dto);

    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "subtotal", target = "subtotal")
    void modificarEntityFromDto(ModDetallePedidosDto mod, @MappingTarget DetallePedidosEntity entity);
}