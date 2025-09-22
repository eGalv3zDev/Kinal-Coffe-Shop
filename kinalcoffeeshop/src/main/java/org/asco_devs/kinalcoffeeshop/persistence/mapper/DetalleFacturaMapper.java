package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.DetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModDetalleFacturaDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.DetalleFacturaEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleFacturaMapper {

    @Mapping(source = "idDetalleFactura", target = "id")
    @Mapping(source = "cantidad", target = "stock")
    @Mapping(source = "subtotal", target = "subStock")
    @Mapping(source = "idFactura", target = "facturaId")
    @Mapping(source = "idProducto", target = "productId")
    DetalleFacturaDto toDto(DetalleFacturaEntity entity);
    List<DetalleFacturaDto> toDto(Iterable<DetalleFacturaEntity> entities);

    @InheritConfiguration
    @Mapping(source  = "stock", target = "cantidad")
    @Mapping(source  = "subStock", target = "subtotal")
    @Mapping(source  = "facturaId", target = "idFactura")
    @Mapping(source  = "productId", target = "idProducto")
    DetalleFacturaEntity toEntity(DetalleFacturaDto dto);

    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subStock", target = "subtotal")
    @Mapping(source = "facturaId", target = "idFactura")
    @Mapping(source = "productId", target = "idProducto")
    void modificarEntityFromDto(ModDetalleFacturaDto mod, @MappingTarget DetalleFacturaEntity entity);
}