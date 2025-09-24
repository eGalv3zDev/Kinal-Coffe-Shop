package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.ModDetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    @Mapping(source = "idDetalle", target = "id")
    @Mapping(source = "idProducto.nombre", target = "productName")
    @Mapping(source = "cantidad", target = "stock")
    @Mapping(source = "subtotal", target = "subTotal")
    @Mapping(source = "idPedido.idPedido", target = "orderId")
    @Mapping(source = "idProducto.idProducto", target = "productId")
    DetallePedidoDto toDto(DetallePedidoEntity entity);
    List<DetallePedidoDto> toDto(Iterable<DetallePedidoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "id", target = "idDetalle")
    @Mapping(target = "idProducto.nombre", ignore = true)
    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subTotal", target = "subtotal")
    @Mapping(source = "orderId", target = "idPedido", ignore = true)
    @Mapping(source = "productId", target = "idProducto", ignore = true)
    DetallePedidoEntity toEntity(DetallePedidoDto dto);

    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subTotal", target = "subtotal")
    void modificarEntityFromDto(ModDetallePedidoDto mod, @MappingTarget DetallePedidoEntity entity);
}