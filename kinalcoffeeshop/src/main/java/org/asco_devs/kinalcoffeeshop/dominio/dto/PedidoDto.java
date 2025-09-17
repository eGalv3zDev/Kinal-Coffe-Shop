package org.asco_devs.kinalcoffeeshop.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoDto(
        Long id,
        @NotNull(message = "La fecha no puede estar vacia")
        LocalDateTime date,
        @NotNull(message = "El total no puede estar vacio")
        @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
        Double total,
        @NotBlank(message = "El estado no puede estar vacio")
        String state,
        Long studentId,
        Long creditUserId
) {
}