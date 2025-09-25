package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ModProductosDto(
        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 64, message = "El nombre no puede exceder los 64 caracteres")
        String nombre,

        String descripcion,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser un número positivo")
        BigDecimal precio,

        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Long stock,

        @FutureOrPresent(message = "La fecha de expiración no puede ser en el pasado")
        LocalDate fechaDeExpiracion,

        @NotNull(message = "El ID de la categoría es obligatorio")
        Long idCategoria
) {
}