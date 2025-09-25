package org.asco_devs.kinalcoffeeshop.dominio.dto.historialCredito;

import org.asco_devs.kinalcoffeeshop.persistence.entity.LineaDeCreditoEntity;
import org.asco_devs.kinalcoffeeshop.persistence.entity.PagoEntity;

import java.time.LocalDate;

public record HistorialCreditoDto(
        Long id,
        LocalDate date,
        String movementType,
        Double amount,
        LineaDeCreditoEntity creditLineId,
        PagoEntity paymentId
) {
}
