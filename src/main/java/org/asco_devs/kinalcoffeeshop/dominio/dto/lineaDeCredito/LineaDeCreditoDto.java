package org.asco_devs.kinalcoffeeshop.dominio.dto.lineaDeCredito;

import org.asco_devs.kinalcoffeeshop.persistence.entity.CuentaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.ProductoEntity;

import java.time.LocalDateTime;

public record LineaDeCreditoDto(
        Long id,
        LocalDateTime date,
        Integer stock,
        Double subTotal,
        ProductoEntity productId,
        CuentaDeCreditoEntity creditAccountId
) {
}