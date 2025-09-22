package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.*;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetallePedidosEntity;
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

    @Mapping(source = "alumno.nombre", target = "alumnoNombre")
    @Mapping(source = "detalles", target = "detalles")
    PedidoConDetallesDto toConDetallesDto(PedidoEntity pedido);

    List<PedidoConDetallesDto> toConDetallesDto(List<PedidoEntity> pedidos);

    @Mapping(source = "producto.nombre", target = "productoNombre")
    @Mapping(source = "pedido.idPedido", target = "idPedido")
    @Mapping(source = "producto.idProducto", target = "idProducto")
    DetallePedidosDto toDetalleDto(DetallePedidosEntity detalle);

    List<DetallePedidosDto> toDetalleDto(List<DetallePedidosEntity> detalles);
}