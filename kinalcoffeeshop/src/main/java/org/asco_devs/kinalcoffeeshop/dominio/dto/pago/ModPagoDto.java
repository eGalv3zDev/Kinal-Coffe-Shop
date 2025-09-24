package org.asco_devs.kinalcoffeeshop.dominio.dto.pago;

import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

public record ModPagoDto(
        String amount,
        String type,
        PedidoEntity orderId
) {
}