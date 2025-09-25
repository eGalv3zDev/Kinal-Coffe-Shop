package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LineasDeCreditoDto(
        Long idConsumo,
        LocalDateTime fecha,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        Long cantidad,

        @NotNull(message = "El subtotal es obligatorio")
        @Positive(message = "El subtotal debe ser un número positivo")
        BigDecimal subtotal,

        @NotNull(message = "El ID del producto es obligatorio")
        Long idProducto,

        @NotNull(message = "El ID de la cuenta es obligatorio")
        Long idCuenta
) {
}