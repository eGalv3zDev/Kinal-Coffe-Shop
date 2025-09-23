package org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito;

import java.time.LocalDateTime;

public record ModLineaDeCreditoDto(
        LocalDateTime date,
        Integer stock,
        Double subTotal
) {
}