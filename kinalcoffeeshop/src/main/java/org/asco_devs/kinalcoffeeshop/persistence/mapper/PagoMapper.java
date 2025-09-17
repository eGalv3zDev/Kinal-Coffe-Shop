package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mappings({
            @Mapping(source = "idPago", target = "id"),
            @Mapping(source = "monto", target = "amount"),
            @Mapping(source = "tipo", target = "type"),
            @Mapping(source = "idPedido", target = "orderId")
    })
    PagoDto toDto(PagoEntity entity);

    List<PagoDto> toDto(Iterable<PagoEntity> entities);

    @Mappings({
            @Mapping(source = "id", target = "idPago"),
            @Mapping(source = "amount", target = "monto"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "orderId", target = "idPedido"),
            @Mapping(target = "fecha", ignore = true),
            @Mapping(target = "pedido", ignore = true)
    })
    PagoEntity toEntity(PagoDto dto);

    @Mappings({
            @Mapping(source = "amount", target = "monto"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "orderId", target = "idPedido"),
            @Mapping(target = "idPago", ignore = true),
            @Mapping(target = "fecha", ignore = true),
            @Mapping(target = "pedido", ignore = true)
    })
    void modificarEntityFromDto(ModPagoDto mod, @MappingTarget PagoEntity entity);
}