package org.asco_devs.kinalcoffeeshop.dominio.dto;

import java.time.LocalDateTime;

public record ModLineaDeCreditoDto(
        LocalDateTime fecha,
        Integer cantidad,
        Double subtotal
) {
}