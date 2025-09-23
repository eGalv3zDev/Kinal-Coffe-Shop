package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido.DetallePedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.ModPedidoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.PedidoConDetalleDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pedido.PedidoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "idPedido", target = "id")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "estado", target = "state")
    @Mapping(source = "idAlumno", target = "studentId")
    @Mapping(source = "idUsuarioCredito", target = "creditUserId")
    PedidoDto toDto(PedidoEntity entity);

    List<PedidoDto> toDto(Iterable<PedidoEntity> entities);

    @InheritConfiguration
    @Mapping(target = "fecha", ignore = true)
    PedidoEntity toEntity(PedidoDto dto);

    @Mapping(source = "total", target = "total")
    @Mapping(source = "state", target = "estado")
    @Mapping(source = "studentId", target = "idAlumno")
    @Mapping(source = "creditUserId", target = "idUsuarioCredito")
    void modificarEntityFromDto(ModPedidoDto mod, @MappingTarget PedidoEntity entity);

    @Mapping(source = "idAlumno.nombre", target = "alumnName")
    @Mapping(source = "detalles", target = "details")
    PedidoConDetalleDto toConDetallesDto(PedidoEntity pedido);

    List<PedidoConDetalleDto> toConDetallesDto(List<PedidoEntity> pedidos);

    @Mapping(source = "idProducto.nombre", target = "productName")
    @Mapping(source = "idPedido.idPedido", target = "id")
    @Mapping(source = "idProducto.idProducto", target = "productId")
    DetallePedidoDto toDetalleDto(DetallePedidoEntity detalle);

    List<DetallePedidoDto> toDetalleDto(List<DetallePedidoEntity> detalles);
}