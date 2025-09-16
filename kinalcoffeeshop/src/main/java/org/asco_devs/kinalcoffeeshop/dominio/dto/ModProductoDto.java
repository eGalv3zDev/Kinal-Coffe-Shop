package org.asco_devs.kinalcoffeeshop.dominio.dto;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;

public record ModProductoDto(
        String name,
        String description,
        Double price,
        Integer categoryId
) {
}
