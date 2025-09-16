package org.asco_devs.kinalcoffeeshop.dominio.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDto(
        Long id,
        @NotBlank(message = "El nombre no puede estar vacio")
        String name
) {
}
