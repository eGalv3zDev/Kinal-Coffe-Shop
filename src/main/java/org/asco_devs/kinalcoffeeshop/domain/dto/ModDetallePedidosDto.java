package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ModDetallePedidosDto(
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que cero")
        Long cantidad,

        @NotNull(message = "El subtotal es obligatorio")
        @Positive(message = "El subtotal debe ser un número positivo")
        BigDecimal subtotal,

        @NotNull(message = "El ID del pedido es obligatorio")
        Long idPedido,

        @NotNull(message = "El ID del producto es obligatorio")
        Long idProducto
) {
}