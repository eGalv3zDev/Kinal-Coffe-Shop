package org.asco_devs.kinalcoffeeshop.dominio.dto.producto;

import jakarta.validation.constraints.*;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;

import java.time.LocalDate;

public record ProductoDto(
        Long id,
        @NotBlank(message = "El nombre no puede estar vacio")
        String name,
        String description,
        @Min(value=0, message = "El precio no puede ser negativo")
        Double price,
        @Min(value=0, message = "El stock no puede ser negativo")
        Integer stock,
        @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
        LocalDate entryDate,
        @Future(message = "La fecha de expiracion debe ser futura")
        LocalDate expirationDate,
        CategoriaEntity categoryId
) {
}
