package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.asco_devs.kinalcoffeeshop.domain.model.TipoPago;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagosDto(
        Long idPago,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser un número positivo")
        BigDecimal monto,

        LocalDateTime fecha,

        @NotNull(message = "El tipo de pago es obligatorio")
        TipoPago tipo,

        @NotNull(message = "El ID del pedido es obligatorio")
        Long idPedido
) {
}