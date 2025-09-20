package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.CuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModCuentaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentaDeCreditoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaDeCreditoMapper {

    @Mapping(source = "idCuenta", target = "id")
    @Mapping(source = "saldo", target = "credit")
    // @Mapping(source = "userId", target = "userId")
    CuentaDeCreditoDto toDto(CuentaDeCreditoEntity entity);
    List<CuentaDeCreditoDto> toDto(Iterable<CuentaDeCreditoEntity> entities);

    @InheritConfiguration
    @Mapping(source = "credit", target = "saldo")
    // @Mapping(source = "userId", target = "userId")
    CuentaDeCreditoEntity toEntity(CuentaDeCreditoDto dto);

    @Mapping(source = "credit", target = "saldo")
    // @Mapping(source = "userId", target = "userId")
    void modificarEntityFromDto(ModCuentaDeCreditoDto mod, @MappingTarget CuentaDeCreditoEntity entity);
}
