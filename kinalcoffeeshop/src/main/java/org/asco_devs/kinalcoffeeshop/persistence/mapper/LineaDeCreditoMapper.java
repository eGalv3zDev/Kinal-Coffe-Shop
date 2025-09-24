package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.LineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito.ModLineaDeCreditoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.LineaDeCreditoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class, CuentaDeCreditoMapper.class})
public interface LineaDeCreditoMapper {

    @Mapping(source = "idConsumo", target = "id")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "cantidad", target = "stock")
    @Mapping(source = "subtotal", target = "subTotal")
    @Mapping(source = "idProducto", target = "productId")
    @Mapping(source = "idCuenta", target = "creditAccountId")
    LineaDeCreditoDto toDto(LineaDeCreditoEntity entity);
    List<LineaDeCreditoDto> toDto(Iterable<LineaDeCreditoEntity> entities);

    @InheritConfiguration
    @Mapping(source = "date", target = "fecha")
    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subTotal", target = "subtotal")
    LineaDeCreditoEntity toEntity(LineaDeCreditoDto dto);

    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "subTotal", target = "subtotal")
    void modificarEntityFromDto(ModLineaDeCreditoDto mod, @MappingTarget LineaDeCreditoEntity entity);
}