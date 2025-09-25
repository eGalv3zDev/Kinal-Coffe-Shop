package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoMovimientoCredito;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HistorialCreditosDto(
        Long idHistorial,

        @NotNull(message = "El ID de la cuenta es obligatorio")
        Long idCuenta,

        LocalDateTime fecha,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimientoCredito tipoMovimiento,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser un número positivo")
        BigDecimal monto,

        Long idConsumo,
        Long idPago
) {
}