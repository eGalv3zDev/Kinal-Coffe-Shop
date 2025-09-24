package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ModProductoDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.producto.ProductoDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "idProducto", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "cantidad", target = "stock")
    @Mapping(source = "fechaDeIngreso", target = "entryDate")
    @Mapping(source = "fechaDeExpiracion", target = "expirationDate")
    @Mapping(source = "idCategoria", target = "categoryId")
    ProductoDto toDto(ProductoEntity entity);

    List<ProductoDto> toDto(Iterable<ProductoEntity> entities);

    @InheritConfiguration
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "price", target = "precio")
    @Mapping(source = "stock", target = "cantidad")
    @Mapping(source = "entryDate", target = "fechaDeIngreso")
    @Mapping(source = "expirationDate", target = "fechaDeExpiracion")
    @Mapping(source = "categoryId", target = "idCategoria")
    ProductoEntity toEntity(ProductoDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "description", target = "descripcion")
    @Mapping(source = "price", target = "precio")
    @Mapping(source = "categoryId", target = "idCategoria")
    void modificarEntityFromDto(ModProductoDto mod, @MappingTarget ProductoEntity entity);
}