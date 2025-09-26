    package org.asco_devs.kinalcoffeeshop.persistence.mapper;

    import org.asco_devs.kinalcoffeeshop.domain.dto.ProductosDto;
    import org.asco_devs.kinalcoffeeshop.persistence.entity.Productos;
    import org.mapstruct.InheritInverseConfiguration;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;

    import java.util.List;

    @Mapper(componentModel = "spring")
    public interface ProductosMapper {

        ProductosDto toDto(Productos producto);
        List<ProductosDto> toDto(List<Productos> productos);

        @InheritInverseConfiguration
        @Mapping(target = "categoria", ignore = true)
        Productos toEntity(ProductosDto dto);
    }