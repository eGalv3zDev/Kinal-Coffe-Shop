package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoMovimientoCredito;
import java.math.BigDecimal;

public record ModHistorialCreditosDto(
        @NotNull(message = "El ID de la cuenta es obligatorio")
        Long idCuenta,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimientoCredito tipoMovimiento,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser un número positivo")
        BigDecimal monto,

        Long idConsumo,
        Long idPago
) {
}

// esta clase no tiene por que usarse, porque no tiene sentido modificar un historial, pero la vamos a dejar para consistencia del proyecto