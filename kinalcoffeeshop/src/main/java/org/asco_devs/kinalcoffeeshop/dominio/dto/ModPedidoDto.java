package org.asco_devs.kinalcoffeeshop.dominio.dto;

import java.math.BigDecimal;

public record ModPedidoDto(
        BigDecimal total,
        String state,
        Long studentId,
        Long creditUserId
) {
}