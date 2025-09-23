package org.asco_devs.kinalcoffeeshop.dominio.dto.detallePedido;


import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;

public record DetallePedidoDto(
        Long id,
        String productName,
        Integer stock,
        Double subTotal,
        Long orderId,
        Long productId
) {}