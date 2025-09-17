package org.asco_devs.kinalcoffeeshop.dominio.dto;

public record ModPagoDto(
        String amount,
        String type,
        Integer orderId
) {
}