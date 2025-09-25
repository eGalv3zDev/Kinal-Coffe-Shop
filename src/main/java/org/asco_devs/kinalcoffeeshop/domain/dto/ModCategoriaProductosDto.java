package org.asco_devs.kinalcoffeeshop.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModCategoriaProductosDto(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(max = 64, message = "El nombre no puede exceder los 64 caracteres")
        String nombre
) {
}