package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(source = "idPago", target = "id")
    @Mapping(source = "monto", target = "amount")
    @Mapping(source = "tipo", target = "type")
    @Mapping(source = "idPedido", target = "orderId")
    PagoDto toDto(PagoEntity entity);
    List<PagoDto> toDto(Iterable<PagoEntity> entities);

    @InheritConfiguration
    @Mapping(target = "fecha", ignore = true)
    PagoEntity toEntity(PagoDto dto);

    @Mapping(source = "amount", target = "monto")
    @Mapping(source = "type", target = "tipo")
    @Mapping(source = "orderId", target = "idPedido")
    void modificarEntityFromDto(ModPagoDto mod, @MappingTarget PagoEntity entity);
}