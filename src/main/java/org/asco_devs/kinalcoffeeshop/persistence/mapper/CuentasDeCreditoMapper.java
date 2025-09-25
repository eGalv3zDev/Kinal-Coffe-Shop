package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.CuentasDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentasDeCredito;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentasDeCreditoMapper {

    CuentasDeCreditoDto toDto(CuentasDeCredito cuenta);
    List<CuentasDeCreditoDto> toDto(List<CuentasDeCredito> cuentas);

    @InheritInverseConfiguration
    @Mapping(target = "usuarioConCredito", ignore = true)
    CuentasDeCredito toEntity(CuentasDeCreditoDto dto);
}