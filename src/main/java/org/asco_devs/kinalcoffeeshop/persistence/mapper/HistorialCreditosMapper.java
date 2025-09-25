package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.HistorialCreditosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.HistorialCreditos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialCreditosMapper {

    HistorialCreditosDto toDto(HistorialCreditos historial);
    List<HistorialCreditosDto> toDto(List<HistorialCreditos> historiales);

    @InheritInverseConfiguration
    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target = "consumo", ignore = true)
    @Mapping(target = "pago", ignore = true)
    HistorialCreditos toEntity(HistorialCreditosDto dto);
}