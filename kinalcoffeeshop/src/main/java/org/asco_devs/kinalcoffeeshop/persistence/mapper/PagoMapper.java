package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.PagoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.pago.ModPagoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(source = "idPago", target = "id")
    @Mapping(source = "monto", target = "amount")
    @Mapping(source = "fecha",target = "date")
    @Mapping(source = "tipo", target = "type")
    @Mapping(source = "idPedido", target = "orderId")
    PagoDto toDto(PagoEntity entity);
    List<PagoDto> toDto(Iterable<PagoEntity> entities);

    @Mapping(source = "amount", target = "monto")
    @Mapping(source = "type", target = "tipo")
    @Mapping(source = "orderId", target = "idPedido")
    PagoEntity toEntity(PagoDto dto);

    @Mapping(source = "amount", target = "monto")
    @Mapping(source = "type", target = "tipo")
    @Mapping(source = "orderId", target = "idPedido")
    void modificarEntityFromDto(ModPagoDto mod, @MappingTarget PagoEntity entity);
}