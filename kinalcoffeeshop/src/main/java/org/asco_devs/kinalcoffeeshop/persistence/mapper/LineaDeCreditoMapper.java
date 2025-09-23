package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineaDeCreditoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class, CuentaDeCreditoMapper.class})
public interface LineaDeCreditoMapper {

    @Mapping(source = "idConsumo", target = "idConsumo")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "subtotal", target = "subtotal")
    @Mapping(source = "producto", target = "producto")
    @Mapping(source = "cuenta", target = "cuenta")
    LineaDeCreditoDto toDto(LineaDeCreditoEntity entity);
    List<LineaDeCreditoDto> toDto(Iterable<LineaDeCreditoEntity> entities);

    @InheritConfiguration
    @Mapping(source = "idConsumo", target = "idConsumo")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "subtotal", target = "subtotal")
    LineaDeCreditoEntity toEntity(LineaDeCreditoDto dto);

    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "subtotal", target = "subtotal")
    void modificarEntityFromDto(ModLineaDeCreditoDto mod, @MappingTarget LineaDeCreditoEntity entity);
}