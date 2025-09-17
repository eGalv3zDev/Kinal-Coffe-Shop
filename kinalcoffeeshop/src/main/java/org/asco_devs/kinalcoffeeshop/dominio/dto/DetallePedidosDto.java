package org.asco_devs.kinalcoffeeshop.dominio.dto;


public record DetallePedidosDto(
        Long idDetalle,
        Integer cantidad,
        Double subtotal,
        Long idPedido,
        Long idProducto
) {}