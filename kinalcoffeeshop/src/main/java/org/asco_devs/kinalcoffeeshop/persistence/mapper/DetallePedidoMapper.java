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

    @Mapping(source = "idPedido.idPedido", target = "orderId")
    @Mapping(source = "idProducto.idProducto", target = "productId")
    DetallePedidoDto toDto(DetallePedidoEntity entity);

    List<DetallePedidoDto> toDto(Iterable<DetallePedidoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "orderId", target = "idPedido.idPedido")
    @Mapping(source = "productId", target = "idProducto.idProducto")
    DetallePedidoEntity toEntity(DetallePedidoDto dto);

    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subTotal", target = "subtotal")
    void modificarEntityFromDto(ModDetallePedidoDto mod, @MappingTarget DetallePedidoEntity entity);
}