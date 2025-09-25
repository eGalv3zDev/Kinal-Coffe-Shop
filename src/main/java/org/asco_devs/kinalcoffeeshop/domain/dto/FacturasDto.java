package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturasDto(
        Long idFactura,
        LocalDateTime fecha,

        @NotNull(message = "El total es obligatorio")
        @Positive(message = "El total debe ser un número positivo")
        BigDecimal total,

        @NotNull(message = "El ID del pedido es obligatorio")
        Long idPedido
) {
}