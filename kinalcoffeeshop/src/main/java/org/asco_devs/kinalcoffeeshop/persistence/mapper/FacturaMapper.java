package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(source="idFactura", target="id")
    @Mapping(source="fecha", target="date")
    @Mapping(source="total", target="total" )
    @Mapping(source="idPedido", target="orderId")
    FacturaDto toDto(FacturasEntity entity);
    List<FacturaDto> toDto(Iterable<FacturasEntity> entities);

    @InheritConfiguration
    @Mapping(source="date", target="fecha")
    @Mapping(source="total", target="total")
    @Mapping(source="orderId", target="idPedido")
    FacturasEntity toEntity(FacturaDto dto);

    @Mapping(source="total", target="total")
    @Mapping(source="orderId", target="idPedido")
    void modificarEntityFromDto(ModFacturaDto mod, @MappingTarget FacturasEntity entity);

}
