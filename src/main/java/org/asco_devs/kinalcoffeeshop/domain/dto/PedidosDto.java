package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.asco_devs.kinalcoffeeshop.domain.model.EstadoPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidosDto(
        Long idPedido,
        LocalDateTime fecha,

        @NotNull(message = "El total del pedido es obligatorio")
        @Positive(message = "El total debe ser un número positivo")
        BigDecimal total,

        @NotNull(message = "El estado del pedido es obligatorio")
        EstadoPedido estado,

        Long idAlumno,
        Long idUsuarioCredito
) {
}