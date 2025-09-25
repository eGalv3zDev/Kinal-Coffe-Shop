package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ModFacturasDto(
        @NotNull(message = "El total es obligatorio")
        @Positive(message = "El total debe ser un número positivo")
        BigDecimal total,

        @NotNull(message = "El ID del pedido es obligatorio")
        Long idPedido
) {
}

// modificar una factura no es legalmente permitido, pero por consistencia del proyecto lo dejaremos