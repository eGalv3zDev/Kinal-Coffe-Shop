package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.domain.dto.UsuariosConCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.UsuariosConCredito;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuariosConCreditoMapper {

    UsuariosConCreditoDto toDto(UsuariosConCredito usuario);
    List<UsuariosConCreditoDto> toDto(List<UsuariosConCredito> usuarios);

    @InheritInverseConfiguration
    UsuariosConCredito toEntity(UsuariosConCreditoDto dto);
}