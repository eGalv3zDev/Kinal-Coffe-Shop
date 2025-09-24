package org.asco_devs.kinalcoffeeshop.dominio.dto.factura;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FacturaDto(
        Long id,
        LocalDateTime date,
        @Min(value=0, message = "El total no puede ser negativo")
        Double total,
        Long orderId

) {
}
