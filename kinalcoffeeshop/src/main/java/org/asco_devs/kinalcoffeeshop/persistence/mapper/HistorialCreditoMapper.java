package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.HistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModHistorialCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.HistorialCreditoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialCreditoMapper {
    @Mapping(source = "idHistorial", target = "id")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "tipoMovimiento", target = "movementType")
    @Mapping(source = "monto", target = "amount")
    // @Mapping(source = "idConsumo", target = "creditLineId")
    @Mapping(source = "idPago", target = "paymentId")
    HistorialCreditoDto toDto(HistorialCreditoEntity entity);
    List<HistorialCreditoDto> toDto(Iterable<HistorialCreditoEntity> entities);

    @InheritConfiguration
    @Mapping(source = "date", target = "fecha")
    @Mapping(source = "movementType", target = "tipoMovimiento")
    @Mapping(source = "amount", target = "monto")
    // @Mapping(source = "creditLineId", target = "idConsumo")
    @Mapping(source = "paymentId", target = "idPago")
    HistorialCreditoEntity toEntity(HistorialCreditoDto dto);

    @Mapping(source = "movementType", target = "tipoMovimiento")
    @Mapping(source = "amount", target = "monto")
    void modificarEntityFromDto(ModHistorialCreditoDto mod, @MappingTarget HistorialCreditoEntity entity);
}
