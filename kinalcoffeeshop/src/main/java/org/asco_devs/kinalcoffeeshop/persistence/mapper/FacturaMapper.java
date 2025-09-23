package org.asco_devs.kinalcoffeeshop.persistence.mapper;

import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.FacturaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.factura.ModFacturaDto;
import org.asco_devs.kinalcoffeeshop.persistence.entity.FacturasEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(source = "idFactura", target = "id")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "idPedido", target = "orderId")
    FacturaDto toDto(FacturasEntity entity);
    List<FacturaDto> toDto(Iterable<FacturasEntity> entities);

    @InheritConfiguration
    @Mapping(source = "date", target = "fecha")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "orderId", target = "idPedido")
    FacturasEntity toEntity(FacturaDto dto);

    @Mapping(source = "total", target = "total")
    @Mapping(source = "orderId", target = "idPedido")
    void modificarEntityFromDto(ModFacturaDto mod, @MappingTarget FacturasEntity entity);

    // Métodos auxiliares para mapear manualmente
    default Long map(PedidoEntity pedido) {
        return pedido != null ? pedido.getIdPedido() : null;
    }

    default PedidoEntity map(Long id) {
        if (id == null) return null;
        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(id);
        return pedido;
    }
}
