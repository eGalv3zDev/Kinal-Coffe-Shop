package org.asco_devs.kinalcoffeeshop.dominio.dto;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;

import java.time.LocalDateTime;

public record LineaDeCreditoDto(
        Long idConsumo,
        LocalDateTime fecha,
        Integer cantidad,
        Double subtotal,
        ProductoEntity producto,
        CuentaDeCreditoEntity cuenta
) {
}