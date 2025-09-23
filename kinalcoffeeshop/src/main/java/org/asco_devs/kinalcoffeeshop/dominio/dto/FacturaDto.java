package org.asco_devs.kinalcoffeeshop.dominio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PedidoEntity;

import java.time.LocalDateTime;

public record FacturaDto(
        Long id,
        @NotNull(message = "La fecha no puede estar vacia")
        LocalDateTime date,
        @Min(value=0, message = "El total no puede ser negativo")
        Double total,
        Long orderId

) {
}
