package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.PagosDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.Pagos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagosMapper {

    PagosDto toDto(Pagos pago);
    List<PagosDto> toDto(List<Pagos> pagos);

    @InheritInverseConfiguration
    @Mapping(target = "pedido", ignore = true)
    Pagos toEntity(PagosDto dto);
}