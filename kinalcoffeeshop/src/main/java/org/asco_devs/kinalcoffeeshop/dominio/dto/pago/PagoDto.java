package org.asco_devs.kinalcoffeeshop.dominio.dto.pago;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

import java.time.LocalDate;

public record PagoDto(
        Long id,
        @NotNull(message = "El monto no puede estar vacio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
        Double amount,
        LocalDate date,
        @NotNull(message = "El tipo de pago no puede estar vacio")
        String type,
        @NotNull(message = "El ID del pedido no puede estar vacio")
        PedidoEntity orderId
) {
}