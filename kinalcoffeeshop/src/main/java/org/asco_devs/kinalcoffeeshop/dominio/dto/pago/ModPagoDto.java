package org.asco_devs.kinalcoffeeshop.dominio.dto.pago;

import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

import java.time.LocalDate;

public record ModPagoDto(
        String amount,
        LocalDate date,
        String type,
        PedidoEntity orderId
) {
}